package creditsaison;

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

import com.creditsaison.tripletsum.dao.TrippletSumDao;
import com.creditsaison.tripletsum.vo.TripletSumVO;
import com.creditsaison.trippletsum.TripletSumServiceImpl;


@RunWith(Parameterized.class)
public class TestTripletSum {

	private TripletSumServiceImpl trippletSumImpl;
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
//		TripletSum tripletSum = new TripletSum();
		trippletSumDao = Mockito.mock(TrippletSumDao.class);
		trippletSumDao.savebeans(null);
		trippletSumImpl = new TripletSumServiceImpl(trippletSumDao);
		
	}

	
	@Test
	public void testCheckTripletSum() {
		TripletSumVO trippletSumRequestVO = new TripletSumVO();
		trippletSumRequestVO.setInputArray(inputArray);
		trippletSumRequestVO.setRequiredSum(totalSum);
		TripletSumVO trippletSumResponseVO = trippletSumImpl.getTripletSum(trippletSumRequestVO);
		assertArrayEquals(outputArray.toArray(), trippletSumResponseVO.getOutputArray().toArray());
	}
	
}
