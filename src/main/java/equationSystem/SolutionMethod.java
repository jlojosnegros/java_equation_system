package equationSystem;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class SolutionMethod {
	//TODO quitar
	// No quitamos este campo porque de hacerlo dejaria de compilar
	// todo hasta dentro de un monton de tiempo
	// Asi que lo que hacemos es poner el miembro que queremos
	// sin quitar el que habia
	protected EquationSystem equationSystem;

	protected List<Equation> equationList;
	protected Set<String> nameSet;

	SolutionMethod() {
		this.nameSet = new HashSet<String>();
	}

	public void set(List<Equation> equationList) {
		this.equationList = equationList;
		for (Equation equation : equationList) {
			for (String name : equation.getNameSet()) {
				this.nameSet.add(name);
			}
		}

	}

	public void set(EquationSystem equationSystem) {
		this.equationSystem = equationSystem;
	}

	public abstract void resolve();
}
