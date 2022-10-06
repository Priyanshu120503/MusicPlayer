
public class DoublyLinkedList<T> 
{
	private class Node<T>
	{
		private T data;
		private Node<T> next;
		private Node<T> prev;
		Node(T val, Node<T> prev, Node<T> next)
		{
			data = val;
			this.next = next;
			this.prev = prev;
		}
		public T getElement()
		{
			return data;
		}
		public Node<T> getNext()
		{
			return next;
		}
		public void setNext(Node<T> n)
		{
			next = n;
		}
		public Node<T> getPrev()
		{
			return prev;
		}
		public void setPrev(Node<T> p)
		{
			prev = p;
		}
	}
	private Node<T> header;
	private Node<T> trailer;
	private Node<T> tempNode;
	private int size;
	DoublyLinkedList()
	{
		header = new Node<>(null, null, null);
		trailer = new Node<>(null, header, null);
		header.setNext(trailer);
		size = 0;
	}
	public void addBetween(T t, Node<T> p, Node<T> n)
	{
		Node<T> newest = new Node<>(t, p, n);
		p.setNext(newest);
		n.setPrev(newest);
		size++;
	}
	public void addFirst(T t)
	{
		addBetween(t, header, header.getNext());
		size++;
	}
	public void addLast(T t)
	{
		addBetween(t, trailer.getPrev(), trailer);
		size++;
	}
	public T removeFirst()
	{
		if(size==0) return null;
		Node<T> temp = header.getNext();
		header.setNext(temp.getNext());
		temp.getNext().setPrev(header);
		size--;
		return temp.getElement();
	}
	public T removeLast()
	{
		if(size==0) return null;
		Node<T> temp = trailer.getPrev();
		trailer.setPrev(temp.getPrev());
		temp.getPrev().setNext(trailer);
		size--;
		return temp.getElement();
	}
	public T first()
	{
		if(size==0) return null;
		return header.getNext().getElement();
	}
	public T last()
	{
		if(size==0) return null;
		return trailer.getPrev().getElement();
	}
	public int size()
	{
		return size;
	}
	public boolean isEmpty()
	{
		return size==0;
	}
	public void display()
	{
		Node<T> temp = header.getNext();
		while(temp!=trailer)
		{
			System.out.print(temp.getElement() + " ");	
			temp = temp.getNext();
		}
	}
	public void reverseDisplay()
	{
		Node<T> temp = trailer.getPrev();
		while(temp!=header)
		{
			System.out.print(temp.getElement() + " ");	
			temp = temp.getPrev();
		}
	}
	public void createTempNodeAtFirst()
	{
		tempNode = header.getNext();
	}
	public void createTempNodeAtLast()
	{
		tempNode = trailer.getPrev();
	}
	public void moveTempAhead()
	{
		if(tempNode == trailer.getPrev()) return;
		tempNode = tempNode.getNext();
	}
	public void moveTempBack()
	{
		if(tempNode == header.getNext()) return;
		tempNode = tempNode.getPrev();
	}
	public T getTempElement()
	{
		return tempNode.getElement();
	}
	public boolean tempCanMoveAhead()
	{
		return tempNode != trailer.getPrev();
	}
	public boolean tempCanMoveBack()
	{
		return tempNode != header.getNext();
	}
}
