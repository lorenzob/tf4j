package nottrz.tf4j.core;

import java.util.Map;
import java.util.Map.Entry;

import org.tensorflow.DataType;
import org.tensorflow.Graph;
import org.tensorflow.Output;
import org.tensorflow.Session;
import org.tensorflow.Session.Runner;
import org.tensorflow.Shape;
import org.tensorflow.Tensor;

// In the fullness of time, equivalents of the methods of this class should be auto-generated from
// the OpDefs linked into libtensorflow_jni.so. That would match what is done in other languages
// like Python, C++ and Go.
public class TensorFlowAPI implements AutoCloseable {
	
	private Graph graph;
	private Session defaultSession;

	public TensorFlowAPI(Graph g) {
		this.graph = g;
	}

	public TensorFlowAPI() {
		graph = new Graph();
	}
	
	public Output div(Output x, Output y) {
		return binaryOp("Div", x, y);
	}

	public Output sub(Output x, Output y) {
		return binaryOp("Sub", x, y);
	}

	public Output resizeBilinear(Output images, Output size) {
		return binaryOp("ResizeBilinear", images, size);
	}

	public Output expandDims(Output input, Output dim) {
		return binaryOp("ExpandDims", input, dim);
	}


	public Output multiply(Output in1, Output in2, String name) {
		return binaryOp("Mul", name, in1, in2);
	}

	public Output add(Output in1, Output in2, String name) {
		return binaryOp("Add", name, in1, in2);
	}

	public Output cast(Output value, DataType dtype) {
		return graph.opBuilder("Cast", "Cast").addInput(value).setAttr("DstT", dtype).build().output(0);
	}

	public Output decodeJpeg(Output contents, long channels) {
		return graph.opBuilder("DecodeJpeg", "DecodeJpeg").addInput(contents).setAttr("channels", channels).build()
				.output(0);
	}

	public Output constant(Object value, String name) {
		Tensor tensorVal;
		if (!(value instanceof Tensor)) {
			tensorVal = Tensor.create(value);
		}
		else {
			tensorVal = (Tensor) value;
		}
		return graph.opBuilder("Const", name).setAttr("dtype", tensorVal.dataType()).setAttr("value", tensorVal).build().output(0);
	}

	public Output variable(Object value, String name) {
		Tensor tensorVal;
		if (!(value instanceof Tensor)) {
			tensorVal = Tensor.create(value);
		}
		else {
			tensorVal = (Tensor) value;
		}
		
		
		long[] shape2 = tensorVal.shape();
		Shape shape = Shape.make(1);
		return graph.opBuilder("Variable", name).setAttr("dtype", tensorVal.dataType()).setAttr("shape", shape).build().output(0);
	}

	private Output binaryOp(String type, Output in1, Output in2) {
		return binaryOp(type, type, in1, in2);
	}

	private Output binaryOp(String type, String name, Output in1, Output in2) {
		return graph.opBuilder(type, name).addInput(in1).addInput(in2).build().output(0);
	}

	public Tensor run(Output out, Map<String, Tensor> dict) {
		Runner runner = defaultSession.runner();
		for (Entry<String, Tensor> e: dict.entrySet()) {
			runner.feed(e.getKey(), e.getValue());
		}
		return runner.fetch(out.op().name()).run().get(0);
	}

	public Tensor run(Output e) {
		return defaultSession.runner().fetch(e.op().name()).run().get(0);
	}
	
	public Tensor run(Output e, Session s) {
		return s.runner().fetch(e.op().name()).run().get(0);
	}

	public Session defaultSession() {
		return defaultSession;
	}

	public Graph graph() {
		return graph;
	}

	public Session newSession() {
		defaultSession = new Session(graph);
		return defaultSession;
	}

	@Override
	public void close() {
		if (defaultSession != null) {
			defaultSession.close();
		}
		graph.close();
	}

	public Output placeholder(DataType dataType, Shape make, String name) {
		return graph.opBuilder("Placeholder", name).setAttr("dtype", dataType).build().output(0);
	}

	public Output reduce_prod(Output in1, String name) {
		Output idx = constant(0, "reduce_prod_idx");
		return graph.opBuilder("Prod", name).addInput(in1).addInput(idx).build().output(0);
	}

	public Output reduce_sum(Output in1, String name) {
		Output idx = constant(0, "reduce_sum_idx");
		return graph.opBuilder("Sum", name).addInput(in1).addInput(idx).build().output(0);
	}

}