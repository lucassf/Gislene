package Proxies;

import java.util.HashSet;
import java.util.Set;

import com.sleepycat.persist.model.DeleteAction;
import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.Relationship;
import com.sleepycat.persist.model.SecondaryKey;

import AOM.Objeto;
import COMM.IProxy;
import COMM.IStorableObject;
import COMM.KeyGenerator;

@Entity
public class PObjeto implements IProxy{
	@PrimaryKey
	Long ID;
	@SecondaryKey(relate = Relationship.MANY_TO_ONE, relatedEntity = PType.class
			, onRelatedEntityDelete = DeleteAction.NULLIFY)
	private Long type;
	@SecondaryKey(relate = Relationship.ONE_TO_MANY, relatedEntity = PProperty.class 
			, onRelatedEntityDelete = DeleteAction.NULLIFY)
	private Set<Long> properties = new HashSet<Long>();
	@SecondaryKey(relate = Relationship.ONE_TO_MANY, relatedEntity = PAccountability.class
			, onRelatedEntityDelete = DeleteAction.NULLIFY)
	private Long accountabilities;
	
	@Override
	public Long getID(){
		return ID;
	};
	
	@Override
	public void setID(){
		this.ID = KeyGenerator.getKey();
	}
	
	public PObjeto(){
		if(ID==null){
			setID();
		}
	}
	
	public PObjeto(Objeto objeto){
		setID();
	}

	@Override
	public IStorableObject construct() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean store(IStorableObject object) {
		return false;		
	}
}
