#include <iostream>
#include <cmath>

float function(float x)
{
    return 2.0f * ((1.0f / (1.0f + x * x)) + (1.0f / (2.0f * x)) - (3.0f * x * x * x));
}

float integrate_rectangles(const float x_min, const float x_max, const float result_analytic,
    const float eps, int& out_num_iterations)
{
    // TODO
    return 1.0f;
}

float integrate_simpson(const float x_min, const float x_max, const float result_analytic,
    const float eps, int& out_num_iterations)
{
    // TODO
    return 1.5;
}

void compare(float result, float result_analytic, int num_iterations, const char* method_name)
{
    const float error = fabsf(result - result_analytic);
    std::cout << "Method : [" << method_name << "], Result : [" << result << "], Error : ["
            << error << "], Num iterations : [" << num_iterations << "]\n";
}

int main(int argc, const char** argv)
{
    static const float x_min = 1.0f;
    static const float x_max = 5.0f;
    static const float eps = powf(10.0f, -4.0f);
    static const float result_analytic = 0.0f; // TODO

    int num_iterations_rectangles = 0;
    int num_iterations_simpson = 0;

    std::cout << "Analytic result : [" << result_analytic << "]\n";

    float result_rectangles = integrate_rectangles(x_min, x_max, result_analytic, eps, num_iterations_rectangles);
    float result_simpson = integrate_simpson(x_min, x_max, result_analytic, eps, num_iterations_simpson);

    compare(result_rectangles, result_analytic, num_iterations_rectangles, "Rectangles");
    compare(result_simpson, result_analytic, num_iterations_simpson, "Simpson");

    return 0;
}