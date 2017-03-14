# tf4j

This is a simple project to learn TensorFlow using the Java API.

At this time the Java API is quite ugly, and misses a simple way to do things.
So I started to write a simple Facade similar to what you get in Python.
This is an example:

		try (TensorFlowAPI tf = new TensorFlowAPI()) {
			
			Output a = tf.constant(5, "input_a");
			Output b = tf.constant(3, "input_b");
			
			Output c = tf.mul(a, b, "mul_c");
			Output d = tf.add(a, b, "add_d");

			Output e = tf.add(c, d, "add_e");

			try (Session s = tf.newSession()) {
				
				Tensor res = tf.run(e);
				out.println(res.intValue());
			}
		}


I'm reading the TensorFlow for Machine Learning and I'm replicating the examples in Java. 




