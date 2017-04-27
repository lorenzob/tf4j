package nottrz.tf4j.samples;

import static java.lang.System.out;
import static nottrz.tf4j.core.TfData.*;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.junit.Test;
import org.tensorflow.DataType;
import org.tensorflow.Graph;
import org.tensorflow.Output;
import org.tensorflow.Session;
import org.tensorflow.Shape;
import org.tensorflow.Tensor;

import nottrz.tf4j.core.TensorFlowAPI;

public class Placeholders {

	@Test
	public void main() {

		try (TensorFlowAPI tf = new TensorFlowAPI()) {
			
			Output a = tf.placeholder(DataType.INT32, Shape.make(2), "my_input");
			
			Output b = tf.reduce_prod(a, "prod_b");
			Output c = tf.reduce_sum(a, "sum_c");
			
			Output d = tf.add(b, c, "add_d");

			try (Session s = tf.newSession()) {
				Map<String, Tensor> dict = dict("my_input", vector(20));
				Tensor res = tf.run(d, dict);
				out.println(res.intValue());
				assertEquals(40, res.intValue());
			}
		}
	}

	private static Map<String, Tensor> dict(String opName, Tensor t) {
		Map<String, Tensor> m = new HashMap<>();
		m.put(opName, t);
		return m;
	}
}
