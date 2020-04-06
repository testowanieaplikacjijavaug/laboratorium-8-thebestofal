import static org.junit.jupiter.api.Assertions.*;
import org.easymock.EasyMock;
import org.easymock.EasyMockExtension;
import org.easymock.EasyMockRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.assertj.core.api.Assertions.*;
@ExtendWith({EasyMockExtension.class})
class AppTest
{
    private App app;
    private Service service;
    
    
    @DisplayName("Example of strict mock success")
    @Test
    void testPowAndFactorialWorking()
    {
        app = new App();
        service = EasyMock.createStrictMock(Service.class);
        app.setService(service);
        
        EasyMock.expect(service.pow(2,3)).andReturn(8.0);
        EasyMock.expect(service.factorial(3)).andReturn(6.0);
        
        EasyMock.replay(service);
        
        assertThat(app.pow(2, 3)).isEqualTo(8.0);
        assertThat(app.factorial(3)).isEqualTo(6.0);
        
        EasyMock.verify(service);
    }
    
    @DisplayName("Example of strict mock fail")
    @Test
    void testPowAndFactorialNotWorking()
    {
        app = new App();
        service = EasyMock.createStrictMock(Service.class);
        app.setService(service);
        
        EasyMock.expect(service.pow(2,3)).andReturn(8.0);
        EasyMock.expect(service.factorial(3)).andReturn(6.0);
        
        EasyMock.replay(service);
        
        assertThatThrownBy(() -> {
            assertThat(app.factorial(3)).isEqualTo(6.0);
            assertThat(app.pow(2, 3)).isEqualTo(8.0);
            EasyMock.verify(service);
        }).isInstanceOf(AssertionError.class);
        
    }
    
    @DisplayName("Example of strict mock fail")
    @Test
    void testUnexpectedCall()
    {
        app = new App();
        service = EasyMock.createStrictMock(Service.class);
        app.setService(service);
        
        EasyMock.replay(service);
    
        assertThatThrownBy(() -> {
            assertThat(app.sqrt(3)).isZero();
            EasyMock.verify(service);
        }).isInstanceOf(AssertionError.class);
       
        
    }
    
    @DisplayName("Example of nice mock same order")
    @Test
    void testSameOrder()
    {
        app = new App();
        service = EasyMock.createNiceMock(Service.class);
        app.setService(service);
    
        EasyMock.expect(service.pow(2,3)).andReturn(8.0);
        EasyMock.expect(service.factorial(3)).andReturn(6.0);
    
        EasyMock.replay(service);
    
        assertThat(app.pow(2, 3)).isEqualTo(8.0);
        assertThat(app.factorial(3)).isEqualTo(6.0);

        EasyMock.verify(service);
       
    }
    
    @DisplayName("Example of nice mock different order")
    @Test
    void testDifferentOrderAndUnexpectedCall()
    {
        app = new App();
        service = EasyMock.createNiceMock(Service.class);
        app.setService(service);
        
        EasyMock.expect(service.pow(2,3)).andReturn(8.0);
        EasyMock.expect(service.factorial(3)).andReturn(6.0);
        
        EasyMock.replay(service);
    
        assertThat(app.factorial(3)).isEqualTo(6.0);
        assertThat(app.pow(2, 3)).isEqualTo(8.0);
        
        //works only in nice type of mock
        assertThat(service.sqrt(2)).isZero();
        EasyMock.verify(service);
        
    }
}