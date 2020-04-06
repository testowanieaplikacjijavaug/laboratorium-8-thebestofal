package zad1;

import zad1.Service;

public class App
{
    private Service service;
    
    public void setService(Service service)
    {
        this.service = service;
    }
    
    public double pow(double a, double b)
    {
        return service.pow(a, b);
    }

    public double factorial(double a)
    {
        return service.factorial(a);
    }
    
    public double sqrt(double a)
    {
        return service.sqrt(a);
    }
}
