package equationSystem;

import java.util.List;

public abstract class SolutionMethod {

	//TODO quitar
	// No quitamos este campo porque de hacerlo dejaria de compilar
	// todo hasta dentro de un monton de tiempo
	// Asi que lo que hacemos es poner el miembro que queremos
	// sin quitar el que habia
	protected EquationSystem equationSystem;

	protected List<Equation> equationList;

	public void set(List<Equation> equationList) {
		this.equationList = equationList;
	}

	public void set(EquationSystem equationSystem) {
		this.equationSystem = equationSystem;
	}

	public abstract void resolve();
}
