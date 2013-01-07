package org.fumin.whosefish;

/*房子的属性*/
public enum Attribute {
	
	Red(1, "red", Type.Color),
	Yellow(2, "yellow", Type.Color),
	Blue(3, "blue", Type.Color),
	Green(4, "green", Type.Color),
	White(5, "white", Type.Color),
	
	Britain(1, "Britain", Type.Nation),
	Norway(2, "Norway", Type.Nation),
	Sweden(3, "Sweden", Type.Nation),
	Germany(4, "Gernmay", Type.Nation),
	Denmark(5, "Denmark", Type.Nation),
	
	Cat(1, "cat", Type.Pet),
	Dog(2, "dog", Type.Pet),
	Fish(3, "fish", Type.Pet),
	Horse(4, "horse", Type.Pet),
	Bird(5, "bird", Type.Pet),
	
	Water(1, "water", Type.Drink),
	Coffee(2, "coffee", Type.Drink),
	Beer(3, "beer", Type.Drink),
	Milk(4, "milk", Type.Drink),
	Tea(5, "tea", Type.Drink),
	
	Dunhill(1, "Dunhill", Type.Cigarette),
	Blends(2, "Blends", Type.Cigarette),
	PallMall(3, "PallMall", Type.Cigarette),
	Prince(4, "Prince", Type.Cigarette),
	BlueMaster(5, "BlueMaster", Type.Cigarette);
	
	int id;
	String value;
	Type type;
	
	Attribute(int id, String value, Type type){
		this.id = id;
		this.value = value;
		this.type = type;
	}
	
	static Attribute getAttr(int id, Type type){
		if(type.equals(Type.Color)){
			if(id==1){
				return Red;
			}else if(id==2){
				return Yellow;
			}else if(id==3){
				return Blue;
			}else if(id==4){
				return Green;
			}else if(id==5){
				return White;
			}else{
				return null;
			}
		}else if(type.equals(Type.Nation)){
			if(id==1){
				return Britain;
			}else if(id==2){
				return Norway;
			}else if(id==3){
				return Sweden;
			}else if(id==4){
				return Germany;
			}else if(id==5){
				return Denmark;
			}else{
				return null;
			}
		}else if(type.equals(Type.Pet)){
			if(id==1){
				return Cat;
			}else if(id==2){
				return Dog;
			}else if(id==3){
				return Fish;
			}else if(id==4){
				return Horse;
			}else if(id==5){
				return Bird;
			}else{
				return null;
			}
		}else if(type.equals(Type.Drink)){
			if(id==1){
				return Water;
			}else if(id==2){
				return Coffee;
			}else if(id==3){
				return Beer;
			}else if(id==4){
				return Milk;
			}else if(id==5){
				return Tea;
			}else{
				return null;
			}
		}else if(type.equals(Type.Cigarette)){
			if(id==1){
				return Dunhill;
			}else if(id==2){
				return Blends;
			}else if(id==3){
				return PallMall;
			}else if(id==4){
				return Prince;
			}else if(id==5){
				return BlueMaster;
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	
	public String getValue(){
		return value;
	}
	
	public int getId(){
		return id;
	}
	
	public Type getType(){
		return type;
	}
}
