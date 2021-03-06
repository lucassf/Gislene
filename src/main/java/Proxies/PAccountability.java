package Proxies;

import java.util.HashSet;
import java.util.Set;

import com.sleepycat.persist.model.DeleteAction;
import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.Relationship;
import com.sleepycat.persist.model.SecondaryKey;

import AOM.Accountability;
import COMM.IProxy;
import COMM.IStorableObject;
import COMM.KeyGenerator;

@Entity
public class PAccountability implements IProxy{
	@PrimaryKey
	private Long ID;
	
	@SecondaryKey(relate = Relationship.MANY_TO_ONE, relatedEntity = PAccountabilityType.class,
			onRelatedEntityDelete = DeleteAction.NULLIFY)
	private Long accountabilityType;
	@SecondaryKey(relate = Relationship.ONE_TO_MANY, relatedEntity = Object.class,
			onRelatedEntityDelete = DeleteAction.NULLIFY)
	private Set<Long> child = new HashSet<Long>();
	@SecondaryKey(relate = Relationship.ONE_TO_ONE)
	private Long parent;
	
	@Override
	public Long getID(){
		return ID;
	};
	
	@Override
	public void setID(){
		this.ID = KeyGenerator.getKey();
	}
	
	public PAccountability(){
		if(ID==null){
			setID();
		}
	}
	
	public PAccountability(Accountability account){
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
