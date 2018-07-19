package model;

public class Pair<T1, T2>
{
	private T1 first;
	private T2 second;
	
	public Pair()
	{
		
	}
	
	public Pair(T1 first, T2 second)
	{
		if (first == null || second == null)
			throw new IllegalArgumentException();
		else
		{
			this.first = first;
			this.second = second;
		}
	}

	public Pair(Pair<T1, T2> pair)
	{
		if (pair == null)
			throw new IllegalArgumentException();
		else
		{
			this.first = pair.first;
			this.second = pair.second;
		}
	}
	
	public void setFirst(T1 first)
	{
		this.first = first;
	}
	
	public void setSecond(T2 second)
	{
		this.second = second;
	}
	
	public T1 getFirst()
	{
		return first;
	}
	
	public T2 getSecond()
	{
		return second;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((second == null) ? 0 : second.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		Pair<T1, T2> other = (Pair<T1, T2>) obj;
		if (first == null)
		{
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (second == null)
		{
			if (other.second != null)
				return false;
		} else if (!second.equals(other.second))
			return false;
		return true;
	}
	
}