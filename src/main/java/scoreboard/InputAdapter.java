package scoreboard;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;

import scoreboard.api.Command;
import scoreboard.api.CommandExecutor;

public class InputAdapter {
	boolean running;
	private Reader reader;
	private HashMap<Character, Command> charToCommand;
	private CommandExecutor executor;

	public InputAdapter(Reader reader, CommandExecutor executor) {
		this.reader = reader;
		this.executor = executor;
		this.running = true;
		this.charToCommand = new HashMap<>();
		this.charToCommand.put('q', Command.IncreaseA);
		this.charToCommand.put('a', Command.DecreaseA);
		this.charToCommand.put('w', Command.IncreaseB);
		this.charToCommand.put('s', Command.DecreaseB);
		this.charToCommand.put('r', Command.Rewind);
		this.charToCommand.put('c', Command.Reset);
	}

	public void runAllCommands() throws IOException {
		while (running) {
			char c = (char)reader.read();
			if (c == 't') {
				this.running = false;
			}
			Command command = this.charToCommand.get(c);
			if (command != null) {
				executor.execute(command);
			}
		}
	}

}
