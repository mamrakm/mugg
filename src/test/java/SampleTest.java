import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.mockito.Mockito.mock;

public class SampleTest {
    final private Logger logger = LoggerFactory.getLogger(SampleTest.class);
    @Test
    public void test1() {
        var l = mock(List.class);
        logger.info("Jeboce?");
        Assertions.assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException();
        });
    }
}
