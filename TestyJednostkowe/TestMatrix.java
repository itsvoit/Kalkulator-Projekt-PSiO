import com.voit.CalculatorApp.Model.MatrixModel.Matrix;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class TestMatrix {

	private static Matrix matrix1;
	private static Matrix matrix2;
	private static Matrix matrix3;
	private static Matrix out1;
	private static Matrix out2;
	private static Matrix out3;

	private static double[] m1 = {1, 2, 3, 1, 2, 3, 1, 2, 3};
	private static double[] m2 = {1, 2, 3, 1, 2, 3, 1, 2, 3};
	private static double[] m3 = {1, 2, 3};
	private static double[] outT1 = {2, 4, 6, 2, 4, 6, 2, 4, 6};
	private static double[] outT2 = {0, 0, 0, 0, 0, 0, 0, 0, 0};
	private static double[] outT3 = {6, 12, 18};

	@BeforeClass
	public static void setup(){
		matrix1 = new Matrix(3, 3, m1);
		matrix2 = new Matrix(3, 3, m2);
		matrix3 = new Matrix(3, 1, m3);
		out1 = new Matrix(3, 3, outT1);
		out2 = new Matrix(3, 3, outT2);
		out3 = new Matrix(3, 1, outT3);
	}

	@Test
	public void testMultiplication(){
		assertTrue(matrix1.multiply(matrix3).equals(out3));
	}

	@Test
	public void testAdd(){
		assertTrue(matrix1.add(matrix2).equals(out1));
	}

	@Test
	public void testSub(){
		assertTrue(matrix1.subtract(matrix2).equals(out2));
	}


}
