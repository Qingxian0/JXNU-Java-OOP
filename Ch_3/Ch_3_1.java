class Triangle{
	private double a,b,c;
	public Triangle(){a=1;b=1;c=1;}//����һ����λ����
	private boolean limit(double x,double y,double z){//�����ε�Լ������
		return (x>0&&y>0&&z>0&&x+y>z&&x+z>y&&y+z>x);
	}
	public void setEdges(double x,double y,double z){
		if(limit(x,y,z)==false)return; //�޸ı�
		else {a=x;b=y;c=z;}
	}
	public String toString(){
		return "a="+a+", b="+b+", c="+c;
	}
	public boolean equals(Triangle t){
		String s=a+","+b+","+c; //���������ӳ��ַ���
		String x,y,z; x=t.a+"";y=t.b+"";z=t.c+"";
		if(s.indexOf(x)<0)return false;
		else s=s.replaceFirst(x,"#");//��s�е�x��#�滻����ɾ�����е�x
		if(s.indexOf(y)<0)return false;
		else s=s.replaceFirst(y,"#");//��s�е�y��#�滻����ɾ�����е�y
		if(s.indexOf(z)<0)return false;
		else return true;
	}
/*
 	public boolean equals(Triangle t){
		double[] x={a,b,c}; double[] y={t.a,t.b,t.c};
		for(int i=0; i<3; i++)
			for(int j=0; j<3; j++)
				if(x[i]==y[j]) y[j]=-1;
		return (y[0]==-1 && y[1]==-1 && y[2]==-1);
	}

 */
}
class App{
	public static void main (String[] args) {Triangle t1,t2,t3;
		t1=new Triangle();
		//����ģ���ܷ�ͨ��t�Ķ���ӿڣ���public��������ֱ�Ӵ�ȡt��private��Ա
		//t.a=2;t.limit(2,3,4);//���������������
		t1.setEdges(1,2,3);	System.out.println("��ֵ1,2,3��t1: "+t1);
		t1.setEdges(2,3,4);	System.out.println("��ֵ2,3,4��t1: "+t1);
		t2=new Triangle();  t2.setEdges(3,4,2); System.out.print("t2: "+t2);
		t3=new Triangle();  t3.setEdges(3,4,5); System.out.println("\tt3: "+t3);
		System.out.print("t1==t2: "+t1.equals(t2));
		System.out.print("\tt1==t3: "+t1.equals(t3));
	}
}
