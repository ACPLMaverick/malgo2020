#include <iostream>
#include <iomanip>
#include <vector>
#include <cmath>

struct function_element
{
    double x;
    double y;
};

struct output_element
{
    double x;
    double y_interpolated;
    double y_accurate;

    double compute_diff() const
    {
        return fabs(fabs(y_interpolated) - fabs(y_accurate));
    }
};

using function_data = std::vector<function_element>;
using output_data = std::vector<output_element>;

void print_function_data(const function_data& data)
{
    std::cout << "Function data:\n";
    std::cout << "x                 | f(x)\n";
    for(const function_element& element : data)
    {
        std::cout << element.x << " | " << element.y << "\n";
    }
}

void print_output_data(const output_data& data)
{
    std::cout << "Output data:\n";
    std::cout << "x                 | i(x)                 | f(x)                 | err \n";
    for(const output_element& element : data)
    {
        std::cout << element.x << " | " << element.y_interpolated << " | "
                << element.y_accurate << " | " << element.compute_diff() << "\n";
    }
}

double compute_component(int i, const function_data& data, const size_t num, double x)
{
    double component = 1.0;
    for(size_t j = 0; j < num; ++j)
    {
        if(i == j)
        {
            continue;
        }

        component = component * ((x - data[j].x) / (data[i].x - data[j].x));
    }

    return component;
}

double compute_value(const function_data& data, double x)
{
    double sum = 0.0;
    const size_t num = data.size();
    for(size_t i = 0; i < num; ++i)
    {
        sum += compute_component(i, data, num, x) * data[i].y;
    }
    return sum;
}

int main(int argc, char** argv)
{
    // https://www.programmersought.com/article/40364990452/
    // https://www.bragitoff.com/2018/01/lagrange-interpolation-polynomial-c-program/

    static const function_data input = 
    {
        { -1.5, -0.69 },
        { -1.0, 0.0 },
        { 0.0, 0.69 },
        { 1.0, 1.09 },
        { 2.0, 1.38 },
        { 5.0, 1.95 }
    };
    static const double x_min = -1.5;
    static const double x_max = 5.0;
    static const double d_x = 0.2;
    
    std::cout << std::fixed << std::setprecision(15);

    print_function_data(input);

    output_data output;
    double x = x_min;

    while(x <= x_max)
    {
        output_element element;

        element.x = x;
        element.y_interpolated = compute_value(input, x);
        element.y_accurate = log(x + 2.0);
        
        output.push_back(element);

        x += d_x;
    }

    print_output_data(output);
    
    return 0;
}