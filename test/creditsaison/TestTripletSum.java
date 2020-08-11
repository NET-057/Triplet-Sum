package creditsaison;

import static org.junit.Assert.assertTrue;

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
	                 { Arrays.asList(12, 3, 4, 1, 6, 9), 24, true},
	                 { Arrays.asList(12, 3, 4, 1, 6, 9), 25, true},
	                 { Arrays.asList(1, 2, 3, 4, 5, 6, 7), 20, true}
	           });
	    }
	   
	@Parameter(0)
	public List<Integer> inputArray;
	 
	@Parameter(1)
	public Integer totalSum;
	 
	@Parameter(2)
	public boolean isContain;
	
	
	
	@Before
	public void setUp() {
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
		boolean isFind = trippletSumResponseVO.getOutputArray().size() > 0 ? true : false;
		assertTrue("Test", isFind == isContain);;
	}
	
}
