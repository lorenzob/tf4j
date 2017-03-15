package nottrz.tf4j.core;

import org.tensorflow.Tensor;

public class TfData {

	public static Tensor vector(int... vals) {
		return Tensor.create(vals);
	}

}
