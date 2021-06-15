#include <iostream>
#include <vector>

struct data_element
{
    float x; // temperature;
    float y; // r_coefficient;
};

using table_data = std::vector<data_element>;

class linear_func
{
public:

    linear_func(float a, float b)
        : m_a(a)
        , m_b(b)
    {
    }

    linear_func(const table_data& input)
    {
        if(input.size() > 0)
        {
            create_from_table_data(input);
        }
        else
        {
            // Invalid input.
            m_a = 0;
            m_b = 0;
        }
    }

    void print()
    {
        std::cout << "R(t) = " << m_a << " * t + " << m_b << "\n";
    }

    void to_table_data(table_data& out_data, float x_from, float x_to, float delta)
    {
        out_data.clear();
        data_element curr_elem{ x_from, 0.0f };
        do
        {
            curr_elem.y = compute_y(curr_elem.x);
            out_data.push_back(curr_elem);
            curr_elem.x += delta;
        } while(curr_elem.x <= x_to);
    }

private:

    void create_from_table_data(const table_data& input)
    {
        // https://medium.com/ml2b/linear-regression-implementation-in-c-acdfb621e56
        // 
        // sum_1 = sum(x_i^2) - n * med_x ^ 2
        // sum_2 = sum(y_i * x_i) - n * med_x * med_y
        // a = sum_2 / sum_1
        // b = med_y - a * med_x

        const data_element med = calculate_median(input);
        const size_t num = input.size();

        float sum_1 = 0.0f;
        float sum_2 = 0.0f;
        for(const data_element& element : input)
        {
            sum_1 += element.x * element.x;
            sum_2 += element.y * element.x;
        }
        sum_1 -= static_cast<float>(num) * med.x * med.x;
        sum_2 -= static_cast<float>(num) * med.x * med.y;

        if(sum_1 != 0.0f)
        {
            m_a = sum_2 / sum_1;
            m_b = med.y - m_a * med.x;
        }
        else
        {
            // Invalid data.
            m_a = 0.0f;
            m_b = 0.0f;
        }
    }

    inline float compute_y(float x)
    {
        return m_a * x + m_b;
    }

    static data_element calculate_median(const table_data& input)
    {
        const size_t num = input.size();
        const size_t num_div_two = num / 2;
        const bool is_odd_num = num % 2 != 0;
        if(is_odd_num)
        {
            return input[num_div_two];
        }
        else
        {
            data_element element;
            element.x = (input[num_div_two].x + input[num_div_two + 1].x) * 0.5f;
            element.y = (input[num_div_two].y + input[num_div_two + 1].y) * 0.5f;
            return element;
        }
    }

    float m_a;
    float m_b;
};

void print_table_data(const table_data& data)
{
    std::cout << "dT | ";
    for(const data_element& element : data)
    {
        std::cout << element.x << " | ";
    }
    std::cout << "\nR  | ";
    for(const data_element& element : data)
    {
        std::cout << element.y << " | ";
    }
    std::cout << "\n";
}

void save_table_data_to_csv(const table_data& data, const char* label)
{
    // TODO
}

int main(int argc, char** argv)
{
    table_data input = 
    {
        { 25.0f, 109.4f },
        { 30.0f, 110.1f },
        { 35.0f, 112.0f },
        { 40.0f, 114.7f },
        { 45.0f, 116.0f },
        { 50.0f, 118.1f },
        { 55.0f, 119.5f },
        { 60.0f, 121.8f },
        { 65.0f, 123.1f },
        { 70.0f, 124.9f },
        { 75.0f, 127.6f },
        { 80.0f, 129.4f },
        { 85.0f, 130.6f },
        { 90.0f, 131.9f },
        { 95.0f, 134.1f },
    };

    // a)
    linear_func func(input);
    func.print();

    // b)
    table_data output;
    func.to_table_data(output, 25.0f, 95.0f, 5.0f);
    print_table_data(output);

    // c)
    save_table_data_to_csv(input, "input");
    save_table_data_to_csv(output, "output");

    return 0;
}