package scoreboard;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.InOrder;

import scoreboard.api.ScoreListener;

public class ScoreWriterTest {

	@Rule
	public final TemporaryFolder tmp = new TemporaryFolder();

	@Test
	public void writeAndForward() throws IOException {
		ScoreListener scoreListener = mock(ScoreListener.class);
		InOrder inOrder = inOrder(scoreListener); 
		File scorefile = tmp.newFile("score.csv");
		ScoreListener scoreWriter = new ScoreWriter(scorefile , scoreListener);
		scoreWriter.onScoreChanged(0, 0);
		verifyScoreFile(scorefile, "0,0");
		inOrder.verify(scoreListener).onScoreChanged(0, 0);
		scoreWriter.onScoreChanged(2, 3);
		inOrder.verify(scoreListener).onScoreChanged(2, 3);
		verifyScoreFile(scorefile, "2,3");
	}

	private void verifyScoreFile(File scorefile, String expectedContent) throws IOException {
		List<String> content = Files.readAllLines(scorefile.toPath());
		assertEquals(1, content.size());
		assertEquals(expectedContent, content.get(0));
	}

	@Test(expected = UncheckedIOException.class)
	public void errorDuringFileAccess() throws Exception {
		File scoreFile = tmp.newFolder();
		ScoreListener scoreListener = mock(ScoreListener.class);
		ScoreListener scoreWriter = new ScoreWriter(scoreFile, scoreListener);
		scoreWriter.onScoreChanged(0, 0);
	}
}
