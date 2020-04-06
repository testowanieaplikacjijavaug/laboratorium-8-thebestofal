package zad4;
import org.easymock.EasyMock;
import org.easymock.EasyMockExtension;
import org.easymock.MockType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(EasyMockExtension.class)
public class CalculatorTest
{
    @Test
    void testWithoutVerify()
    {
        Calculator calc = mock(Calculator.class);
        expect(calc.add(2, 3)).andReturn(5.0);
        expect(calc.sub(3,2)).andReturn(1.0);
        replay(calc);
        
        assertEquals(1.0, calc.sub(3, 2));
    }
    
    @Test
    void testWithVerify()
    {
        Calculator calc = mock(Calculator.class);
        expect(calc.add(2, 3)).andReturn(5.0);
        expect(calc.sub(3,2)).andReturn(1.0);
        replay(calc);
    
        assertEquals(1.0, calc.sub(3, 2));
    
        assertThatThrownBy(() -> {
            //verify wywołany, a nie wszystkie metody zostały wywołane (add)
            //więc będzie AssertionError
            verify(calc);
        }).isInstanceOf(AssertionError.class);
    }
}
