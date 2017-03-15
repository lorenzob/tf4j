package nottrz.tf4j.samples;

import static java.lang.System.out;
import static nottrz.tf4j.core.TfData.*;

import org.tensorflow.Graph;
import org.tensorflow.Output;
import org.tensorflow.Session;
import org.tensorflow.Tensor;

import nottrz.tf4j.core.TensorFlowAPI;

import org.tensorflow.Graph;
import org.tensorflow.Output;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import static java.lang.System.out;

import org.tensorflow.Graph;
import org.tensorflow.Output;
import org.tensorflow.Session;
import org.tensorflow.Tensor;

public class TensorReduce {

	public static void main(String[] args) {

		try (TensorFlowAPI tf = new TensorFlowAPI()) {
			
			Output a = tf.constant(vector(5, 3), "input_a");
			
			Output b = tf.reduce_prod(a, "prod_b");
			Output c = tf.reduce_sum(a, "sum_c");
			
			Output d = tf.add(b, c, "add_d");

			try (Session s = tf.newSession()) {
				
				Tensor res = tf.run(d);
				out.println(res.intValue());
			}
		}
	}
}
