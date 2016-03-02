package scoreboard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;

import scoreboard.api.CommandExecutor;

public class Starter {

	public static void main(String[] args) throws IOException {
		Reader reader = new InputStreamReader(System.in);
		PrintStream out = System.out;
		new Starter().run(reader, out);
	}

	void run(Reader reader, PrintStream out) throws IOException, FileNotFoundException {
		ScoreDisplay display = new ScoreDisplay(out);
		ScoreWriter scoreWriter = new ScoreWriter(new File("target/score.csv"), display);
		CommandExecutor scoreBoard = new Scoreboard(scoreWriter);
		HistoryExecutor history = new HistoryExecutor(scoreBoard);
		File historyFile = new File("target/history");
		historyFile.createNewFile();
		history.replay(historyFile);
		InputAdapter input = new InputAdapter(reader, history);
		input.runAllCommands();
		history.write(historyFile);
	}

}
