package scoreboard;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;

public class StarterTest {

	@Test
	public void testRun() throws FileNotFoundException, IOException {
		Reader reader = new StringReader("qawsrct");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(baos);
		new Starter().run(reader, out);
		String result = baos.toString("utf-8");
		assertEquals("000 : 000\n" + 
				"001 : 000\n" + 
				"000 : 000\n" + 
				"000 : 001\n" + 
				"000 : 000\n" + 
				"000 : 000\n" + 
				"000 : 001\n" + 
				"000 : 000\n", result);
	}
}
