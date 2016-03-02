package scoreboard;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;
import org.mockito.InOrder;

import scoreboard.api.Command;

public class InputAdapterTest {

	@Test
	public void controlViaStdIn() throws Exception {
		Scoreboard scoreBoard = mock(Scoreboard.class);
		InOrder inOrder = inOrder(scoreBoard);
		Reader reader = new StringReader("qawsrct");
		InputAdapter input = new InputAdapter(reader, scoreBoard);
		input.runAllCommands();
		inOrder.verify(scoreBoard).execute(Command.IncreaseA);
		inOrder.verify(scoreBoard).execute(Command.DecreaseA);
		inOrder.verify(scoreBoard).execute(Command.IncreaseB);
		inOrder.verify(scoreBoard).execute(Command.DecreaseB);
		inOrder.verify(scoreBoard).execute(Command.Rewind);
		inOrder.verify(scoreBoard).execute(Command.Reset);
	}
}
