package creditSaison;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mockito;

import com.creditsaison.trippletsum.TrippletSumServiceImpl;
import com.creditsaison.trippletsum.dao.TrippletSumDao;
import com.creditsaison.trippletsum.vo.TrippletSumVO;

@RunWith(Parameterized.class)
public class TestTrippletSum {

	
	private TrippletSumServiceImpl trippletSumImpl;
	private TrippletSumDao trippletSumDao;
	
	 @Parameters
	 public static Collection<Object[]> data() {
	        return Arrays.asList(new Object[][] { 
	                 { Arrays.asList(1,2,3,4,5,6,7), 20, Arrays.asList(3,4,5) },
	                 { Arrays.asList(1,2,3,4,5,6,7), 20, Arrays.asList(3,4,5) },
	                 { Arrays.asList(1,2,3,4,5,6,7), 20, Arrays.asList(3,4,5) },
	                 { Arrays.asList(1,2,3,4,5,6,7), 20, Arrays.asList(3,4,5) }
	           });
	    }
	   
	@Parameter(0)
	public List<Long> inputArray;
	 
	@Parameter(1)
	public Long totalSum;
	 
	@Parameter(2)
	public List<Long> outputArray;
	
	
	
	@Before
	public void setUp() {
		trippletSumDao = Mockito.mock(TrippletSumDao.class);
		Mockito.when(trippletSumDao.save(null)).thenReturn(null);
		trippletSumImpl = new TrippletSumServiceImpl(null);
		
	}
	
	@Test
	public void testCheckTrippletSum() {
		TrippletSumVO trippletSumRequestVO = new TrippletSumVO();
		trippletSumRequestVO.setInputArray(inputArray);
		trippletSumRequestVO.setRequiredSum(totalSum);
		TrippletSumVO trippletSumResponseVO = trippletSumImpl.getTrippletSum(trippletSumRequestVO);
		assertArrayEquals(outputArray.toArray(), trippletSumResponseVO.getOutputArray().toArray()));
	}
	
}
