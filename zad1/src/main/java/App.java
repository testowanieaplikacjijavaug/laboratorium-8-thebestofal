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
}
