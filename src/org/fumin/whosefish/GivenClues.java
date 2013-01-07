package org.fumin.whosefish;

import java.util.ArrayList;

public class GivenClues extends ArrayList<Clue>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int pointsAmount;
	
	public GivenClues(){
		add(new BindClue(Attribute.Red, Attribute.Britain));
		add(new BindClue(Attribute.Sweden, Attribute.Dog));
		add(new BindClue(Attribute.Denmark, Attribute.Tea));
		add(new BindClue(Attribute.Green, Attribute.Coffee));
		add(new BindClue(Attribute.Bird, Attribute.PallMall));
		add(new BindClue(Attribute.Yellow, Attribute.Dunhill));
		add(new BindClue(Attribute.Beer, Attribute.BlueMaster));
		add(new BindClue(Attribute.Germany, Attribute.Prince));
		
		add(new FixedClue(3, Attribute.Milk));
		add(new FixedClue(1, Attribute.Norway));
		
		add(new AdjacentClue(Attribute.White, Attribute.Green, -1));
		add(new AdjacentClue(Attribute.Cat, Attribute.Blends, 0));
		add(new AdjacentClue(Attribute.Horse, Attribute.Dunhill, 0));
		add(new AdjacentClue(Attribute.Blue, Attribute.Norway, 0));
		add(new AdjacentClue(Attribute.Water, Attribute.Blends, 0));/**/		
	}
	
	@Override
	public boolean add(Clue e) {
		// TODO Auto-generated method stub
		pointsAmount+=e.points;
		return super.add(e);
	}

}
