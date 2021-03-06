package AOM;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import COMM.IStorableObject;

@SuppressWarnings(value = { "unused" })
public class Objeto implements ITypePatternListener, IStorableObject {
	private final String name;
	private Geo posicao;
	private HashMap<PropertyType, Property> properties;
	private HashMap<AccountabilityType, Accountability> accountabilities;
	
	private Type tipo;
	
	public Objeto(String name, String posicao, Type tipo) throws IOException{
		this.name = name;
		this.posicao = new Geo(posicao);
		properties = new HashMap<PropertyType, Property>();
		accountabilities = new HashMap<AccountabilityType, Accountability>();
		this.tipo = tipo;
	}
	
	public Geo getGeo() throws IOException{
		return new Geo(posicao.getPointsString());
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public boolean addProperty(PropertyType pType){
		if(properties.containsKey(pType)){
			return false;
		}
		properties.put(pType, new Property(pType));
		return true;
	}

	@Override
	public boolean setProperty(PropertyType pType, String value){
		Property prop = properties.get(pType);
		return (prop==null)? false: prop.setValue(value);
	}

	@Override
	public boolean removeProperty(PropertyType pType) {
		return properties.remove(pType) != null;
	}

	@Override
	public boolean addAccountability(AccountabilityType aType) {
		if(accountabilities.containsKey(aType)){
			return false;
		}
		accountabilities.put(aType, new Accountability(aType, this));
		return true;
	}

	@Override
	public boolean setAccountabilityChild(AccountabilityType aType, ITypePatternListener child) {
		Accountability account = accountabilities.get(aType);
		return (account==null)? false:account.setChild(child);
	}

	@Override
	public boolean removeAccountability(AccountabilityType aType) {
		return accountabilities.remove(aType) != null;
	}
	
	@Override
	public boolean loseChild(AccountabilityType aType, ITypePatternListener lostChild){
		Accountability account = accountabilities.get(aType);
		return (account==null)? false: account.setChild(null);
	}

	@Override
	public boolean checkIfReciprocal(AccountabilityType accountabilityType) {
		return accountabilities.containsKey(accountabilityType);
	}

	@Override
	public void erase() {
		posicao = null;
		properties = null;
		Iterator<Accountability> iterator = accountabilities.values().iterator();
		while(iterator.hasNext()){
			iterator.next().erase();
			iterator.remove();
		}
		accountabilities = null;
	}
}
