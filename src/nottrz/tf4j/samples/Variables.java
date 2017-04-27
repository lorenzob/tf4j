package nottrz.tf4j.samples;

import static java.lang.System.out;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.tensorflow.Graph;
import org.tensorflow.Output;
import org.tensorflow.Session;
import org.tensorflow.Tensor;

import nottrz.tf4j.core.TensorFlowAPI;

public class Variables {

	@Test
	public void main() {

		try (TensorFlowAPI tf = new TensorFlowAPI()) {
			
			Output a = tf.variable(5, "input_a");
			Output b = tf.constant(3, "input_b");
			
			Output c = tf.multiply(a, b, "mul_c");
			Output d = tf.add(a, b, "add_d");

			Output e = tf.add(c, d, "add_e");

			try (Session s = tf.newSession()) {
				
				Tensor res = tf.run(e);
				out.println(res.intValue());
				assertEquals(23, res.intValue());
			}
		}
	}
}
