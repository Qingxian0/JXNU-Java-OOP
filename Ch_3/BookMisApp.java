/*��������
 *ĳͼ���Ҹ�����Ժ��ʦ��ѧ���ṩͼ����ķ���ͼ������һ�������ʽ�ĵǼǲᣬ
 *�����Ŀ�������߱�ţ�ѧ�Ż��߽̺ţ���������������ISBN���������ڡ��������ڡ�
 *ÿ��������һ�Ž��鿨����¼���˽��������������ISBN���������ڡ��������ڣ���
 *��ͬ������Ա����ʦ/ѧ�����в�ͬ�Ľ����������ƣ�
 *��ÿλѧ��/��ʦδ��ͼ������2/4�������򽫲�������顣
 *����ʱ��Ҫ��д���ߵĽ��鿨��ͼ���ҵĵǼǲᣬ����ʱ���ڽ��鿨���Ǽǲ������Ŀ����д�������ڡ�
 *�����ͼ���ҹ���Ա���ڵǼǲἰ���߽��鿨�Ķ�Ӧ��Ŀ�ϸ��¡�
 **/
import java.io.File;
import java.util.Scanner;
import java.time.LocalDate;
//�������������л����ࡢ�ӿڣ��ֽ׶ζ��߿ɲ������
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
/*�ַ���������StringFormat
 *��ɫ�������ڰ��������ַ����ַ������и�ʽ�����
 **/
class StringFormat{//�Զ����ʽ���ַ��������ڸ�ʽ���������ֵ��ַ���
    static boolean isChinese(char c) {//ʶ���Ƿ�Ϊ����
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION)
            return true;
        return false;
    }
	static int length(String s){//s��ʵ��ռ�ÿ�ȣ�һ������ռ�����ַ�λ��
		char[] chAr = s.toCharArray();
		int c=0;
		for(char x: chAr)
			c=(isChinese(x))?c+2:c+1;
		return c;
	}
	static String stringHead(String s, int len){//ȡs�д�ͷ��ʼ��len���ַ���ȣ�ע������ռ2���ַ���
		char[] chAr = s.toCharArray();
		int c=0,i=0;
		for( ; i<chAr.length&&c<len; i++)//��c������Ҫ��ʱ��i������ȡ�ַ���ʵ������
			c=(isChinese(chAr[i]))?c+2:c+1;//c���ڼ���ʵ�ʿ��
		return s.substring(0,i);//substring(0,i)Դ��String����ͷȡi���ַ�
	}
	static String repeat(char c, int n){//���ַ�c�ظ�n��
		if(n<=0) return "";
		String s="";
		for(int i=0; i<n; i++)s=s+c;
		return s;
	}
	static String formatR(String s, int n, char c ){//�Ҷ��룺��s�����ܺ��к��֣����ɳ���Ϊn���ַ�����c������ַ�����������
		int len=length(s);
		if(len>=n) return stringHead(s,n);
		return repeat(c,n-len)+s;
	}
	static String formatL(String s, int n, char c ){//����룺��s�����ܺ��к��֣����ɳ���Ϊn���ַ�����c������ַ����������Ҳ�
		int len=length(s);
		if(len>=n) return stringHead(s,n);
		return s+repeat(c,n-len);
	}
	static String formatC(String s, int n, char c ){//���ж��룺��s�����ܺ��к��֣����ɳ���Ϊn���ַ�����c������ַ����������Ҳ�
		int left,right,len=length(s);
		if(len>=n) return stringHead(s,n);
		left=(n-len)/2; right=n-len-left;
		return repeat(c,left)+s+repeat(c,right);
	}
}
/*ͼ���ʽ����BookMisFormat�����ڱ�ϵͳ��������Ҫ�����������ڴ˸�ʽ��
 *��ν��ʽ�������Ƿ���ָ����ȺͶ��뷽ʽ���ַ�����
 *��ͼ��������ʽ�������ǽ�����ת��ɹ̶����ȡ��̶����뷽ʽ���ַ���
 *���⣬������ͼ�顢���ߡ�������Ŀ��Ϣ����ı�����
 **/
class BookMisFormat{//�Ա�ϵͳ�е��������ݵĸ�ʽ���������������ı�����
	//����ͼ�顢���ߡ����ļ�¼��ʾʱ�ı������ַ���
	static String bookItemTitleStr,readerItemTitleStr,borrowItemTitleStr;
	//��������ʶռ�õĿ��
	static int numWidth,authorWidth,bookTitleWidth,pressWidth,
	    isbnWidth,dateWidth,readerIDWidth,readerNameWidth,readerCategoryWidth;
	static String getBookTitle(){  return bookItemTitleStr; }
	static String getReaderTitle(){ return readerItemTitleStr; }
	static String getBorrowTitle(){ return borrowItemTitleStr; }
	//�����԰�ָ����ȶ���
	static void setWidth(int wz,int sm,int zz, int cbs, int isbn, int rq, int sl,int id, int xm, int lb){
		numWidth=wz; bookTitleWidth=sm; authorWidth=zz; pressWidth=cbs; isbnWidth=isbn; dateWidth=rq; numWidth=sl;
		readerIDWidth=id; readerNameWidth=xm; readerCategoryWidth=lb;
		//ͼ����Ϣ������bookItemHeadInfo��λ�á����������ߡ������硢�������ڡ�ISBN���������ڿ⡢����
		//������Ϣ������readerItemHeadInfo����š����������
		//���ļ�¼������borrowItemHeadInfo����� ���� ��� ���� ���� ������ ISBN ��������  ��������
	}
	static String position(int pos){ return String.format("%0"+numWidth+"d",pos); }//��ˮ�ţ�ͼ��������е��±�
	static String bookTitle(String sm){ return StringFormat.formatL(sm,bookTitleWidth,' '); }
	static String author(String zz){ return StringFormat.formatL(zz,authorWidth,' '); }
	static String press(String cbs){ return StringFormat.formatL(cbs,pressWidth,' '); }
	static String ISBN(String i){ return StringFormat.formatL(i,isbnWidth,' '); }
	static String date(LocalDate rq){
		if(rq==null)return StringFormat.formatC("δ֪",dateWidth,' ');
		else return StringFormat.formatL(rq+"",dateWidth,' '); }
	static String num(int sL){ return StringFormat.formatC(sL+"",numWidth,' '); }
	static String readerID(String id){ return StringFormat.formatL(id,readerIDWidth,' '); }
	static String readerName(String xm){ return StringFormat.formatL(xm,readerNameWidth,' '); }
	static String readerCategory(String lb){ return StringFormat.formatL(lb,readerCategoryWidth,' '); }
	static void creatAllHeadLineStr(){
		/*ͼ����Ϣ��������ʾ���£�
		 * ��ˮ�š����������ߡ������硢�������ڡ�ISBN���������ڿ⡢����
		 *--------------------------------------------------------------
		 **/
		bookItemTitleStr=StringFormat.formatC("��ˮ��",numWidth,' ')+StringFormat.formatC("����",bookTitleWidth,' ')
			+StringFormat.formatC("����",authorWidth,' ')+StringFormat.formatC("������",pressWidth,' ')
			+StringFormat.formatC("��������",dateWidth,' ')+StringFormat.formatC("ISBN",isbnWidth,' ')
			+StringFormat.formatC("����",numWidth,' ')+StringFormat.formatC("�ڿ�",numWidth,' ')
			+StringFormat.formatC("����",numWidth,' ')+"\n";
		//����ͼ������еĿ�ȣ�����������������еĸ����ȶ�Ӧ
		int bookItemHeadInfoWidth=numWidth+bookTitleWidth+authorWidth+pressWidth
			  +dateWidth+isbnWidth+numWidth+numWidth+numWidth;
		bookItemTitleStr=bookItemTitleStr+StringFormat.repeat('-',bookItemHeadInfoWidth)+"\n";
		/*������Ϣ��������ʾ���£�
		 * ��� ���� �Ա�
		 *----------------
		 **/
		readerItemTitleStr=StringFormat.formatC("���",readerIDWidth,' ')
			+StringFormat.formatC("����",readerNameWidth,' ')+StringFormat.formatC("���",readerCategoryWidth,' ')+"\n";
		int readerItemTitleStrWidth=readerIDWidth+readerNameWidth+readerCategoryWidth;
		readerItemTitleStr=readerItemTitleStr+StringFormat.repeat('-',readerItemTitleStrWidth)+"\n";
		/*������Ϣ��������ʾ���£�
		 * ��ˮ�� ��� ���� ��� ���� ���� ������ ISBN ��������  ��������
		 *----------------------------------------------------------------
		 **/
		borrowItemTitleStr=StringFormat.formatC("��ˮ��",numWidth,' ')+StringFormat.formatC("���",readerIDWidth,' ')
			+StringFormat.formatC("����",readerNameWidth,' ')+StringFormat.formatC("���",readerCategoryWidth,' ')
			+StringFormat.formatC("����",bookTitleWidth,' ')+StringFormat.formatC("����",authorWidth,' ')
			+StringFormat.formatC("������",pressWidth,' ')+StringFormat.formatC("ISBN",isbnWidth,' ')
			+StringFormat.formatC("��������",dateWidth,' ')+StringFormat.formatC("��������",dateWidth,' ')+"\n";
		int borrowItemTitleStrWidth=numWidth+readerIDWidth+readerNameWidth+readerCategoryWidth
			   +bookTitleWidth+authorWidth+pressWidth+isbnWidth+dateWidth+dateWidth;
		borrowItemTitleStr=borrowItemTitleStr+StringFormat.repeat('-',borrowItemTitleStrWidth)+"\n";
	}
	static void init(){//��ʼ��
		setWidth(6,32,14,20,14,11,5,13,10,4); //ע�⣬Ҫ�ȸ����ʵ�ʿ���Դ����⼷��һ��
			//������Ӧ����Ϊ����ˮ�š����������ߡ������硢ISBN�����ڡ����������߱�š�������������ͬ�����ߣ����������
			//��ˮ�ž���ͼ��������е�λ�ñ��
		creatAllHeadLineStr();//������������
	}
}
/*ͼ���ࡤ����ͼ��Ӧ�߱��Ļ�����Ϣ�Ͳ���
 *�漰������Ϣ���������������ߡ������硢ISBN���������ڵȻ�����Ϣ
 *���⣬���ǵ����ģ������ˣ�ĳһ����ͼ���������ڿ��������Լ����Ĵ���������ͳ�����ܻ�ӭ��ͼ�飩
 *Ϊ����ƣ���ͼ����Ϣ��������Ϊ�̶�����toString()
 **/
class Book implements Serializable{
	String title,author,press,ISBN;//���������ߡ������硢ISBN
	LocalDate publicationDate;     //��������
	int total,unreturnNum,borrowCount;  //ͼ����������ڿ����������Ĵ���
	Book(String sm,String zz,String cbs,LocalDate cbrq,String isbn,int zs, int dqs, int jycs){
	 //ע�⣺����ScannerĬ���ÿո���Ϊ�ָ���������������ַ������ܳ��ֿո񣬷����ȡ�ļ�������
		title=sm; ISBN=isbn; author=zz; press=cbs;  publicationDate=cbrq;
		total=zs; unreturnNum=dqs; borrowCount=jycs;
	}
	String getTitle(){ return title; }
	void setTitle(String s){ title=s; }
	String getISBN(){ return ISBN; }
	void setISBN(String s){ ISBN=s; }
	String getAuthor(){ return author; }
	void setAuthor(String s){ author=s; }
	String getPress(){ return press; }
	void setPress(String s){ press=s; }
	int getTotal(){ return total; };
	void setTotal(int x){ total=x; }
	int getUnreturnNum() { return unreturnNum; }
	void setUnreturnNum(int x){ unreturnNum=x; }
	int getborrowCount() { return borrowCount; }
	void setborrowCount(int x){ borrowCount=x; }
	LocalDate getPublicationDate(){ return publicationDate; }
	void setPublicationDate(LocalDate d){ publicationDate=d; }
	public 	String toString(){
		//��ʽ������ ���� ������ �������� ISBN ���� �ڿ����� ���Ĵ���
		return BookMisFormat.bookTitle(title)+BookMisFormat.author(author)
			+BookMisFormat.press(press)+BookMisFormat.date(publicationDate)
			+BookMisFormat.ISBN(ISBN)+BookMisFormat.num(total)
			+BookMisFormat.num(unreturnNum)+BookMisFormat.num(borrowCount);
	}
}
/*ͼ�����BookDB����˳�����֯ͼ��
**/
class BookDB implements Serializable{
	final int maxNum; //Ĭ��ͼ����������
	int length;//ͼ���ʵ������
	Book[] db;
	BookDB(int max){ maxNum=max; db=new Book[max]; }
	int find(Book b){//����ͼ���е�ISBN���ң��������Ψһ��
		//Ϊ�������ò�ѯ�����ﷵ��λ����Ϣ��-1��ʾ������
		if(b==null)return -1;
		for(int i=0; i<length; i++)
			if(db[i].getISBN().equals(b.getISBN())) return i;
		return -1;
	}
	void find(String bookName){//���������ַ������ң�����ģ�����ҿ�����һ���¼��Ϊ�����������ֱ��������ҽ����
		//��ʾ���а����ַ���s�ļ�¼
		System.out.print(BookMisFormat.getBookTitle());
		boolean flag=false;
		for( int i=0; i<length; i++)
			if(db[i].getTitle().indexOf(bookName)>=0){//���ҵ�ƥ���ͼ��
				flag=true;
				System.out.println(BookMisFormat.position(i)+" "+db[i]);}
		if(flag==false)
			System.out.println("----û�ҵ��κ�ƥ���ͼ��-----");
	}
	void add(Book b){//������ͼ�������ѷ�����b��
		if(length==maxNum) {
			System.out.println("\n�����������������������������ͼ�飡");
			return ;
		}
		int pos=find(b);//���ǰ�ȿ�ͼ���Ƿ���ڣ�ע��ISBN��ͼ���Ψһ��ʶ
		if(pos==-1){ //ͼ�鲻����
			db[length]=b; length++;
			return;
		}
		if(pos>=0){ //ͼ���Ѵ��ڣ�������������棬�������޸ı�
			db[pos].setTotal( b.getTotal()+db[pos].getTotal() );
			db[pos].setUnreturnNum( b.getTotal()+db[pos].getUnreturnNum());
		}
	}
	Book getBook(int pos){//��ȡposλ���ϵ�ͼ��
		if(pos<0||pos>=length)return null;
		return db[pos];
	}
	void showInfo(){//��ʾ����ͼ����Ϣ
		System.out.print(BookMisFormat.getBookTitle());
		for(int i=0; i<length; i++)
			System.out.println(BookMisFormat.position(i)+" "+db[i]);
	}
	void showInfo(int pos){ //��ʾ�±�Ϊpos��ͼ����Ϣ
		if(pos<0||pos>=length)return;
		System.out.print(BookMisFormat.getBookTitle());
		System.out.println(BookMisFormat.position(pos)+" "+db[pos]);
	}
	static BookDB readBookDBFromFile(String fileName, int max)throws Exception{//���ı��ļ���ȡ����ͼ����Ϣ
		BookDB bookDB =new BookDB(max);
		String bookTitle,author,press,ISBN,cbrq;
		LocalDate publicationDate;
		int total,unreturnNum,borrowCount;
		Scanner sc=new Scanner(new File(fileName));
		sc.nextLine();		sc.nextLine(); //�ļ���ǰ�����Ǳ����У���������
		Book b; int i;
		for(i=0;sc.hasNext(); i++){ //ע��hasNext()��hasNextLine()�����ƶ���ȡ��ָ�룬���ж� sc.hasNextLine()&&
		//���⣬��ʱhasNextLine()Ϊ��ʱ�����治һ�������ݣ�����еȣ����ڱ�ѭ���ĵ�һ�������sc.next()����˼�����hasNext()
			bookTitle=sc.next(); author=sc.next(); press=sc.next();
			cbrq=sc.next();  publicationDate=LocalDate.parse(cbrq);  ISBN=sc.next();
			total=sc.nextInt();   unreturnNum=sc.nextInt();   borrowCount=sc.nextInt();
			b= new Book(bookTitle,author,press,publicationDate,ISBN,total,unreturnNum,borrowCount);
			bookDB.db[i]=b;
		}
		bookDB.length=i; //��¼��ȡͼ�������
		return bookDB;
	}
}
/*������Reader
 *  ������Ϣ����š����������0/1��ѧ��/��ʦ�������鿨��˳���
 *    ������Ϣ�����鿨����������δ�����������
 *  �������������������Ե�get/set
 * ��˵����
 *1�����ߵĽ��鿨�洢�����Լ��Ľ�����Ϣ���Ǽǲ��д洢�������ж��ߵĽ�����Ϣ��
 *   ע�⣺���鿨���Ǽǲ��У�������ʵ������ͬһ��������Ŀ����
 *     �������ڻ���ʱ��ֻ���޸Ľ�����Ŀ�����漰�����Ŀ�����Ľ��鿨���Ǽǲ᣻
 *2��Ϊ���㻹�飬��������������
 *   a. showUnreturn()����ʾ�ö��ߵ�����δ����¼
 *   b. getBorrowItem(pos)�����ض��߽��鿨��posλ�õĽ�����Ŀ
 **/
class Reader implements Serializable{//����:��������š���𡢹������鿨
	String name,ID;
	int tag; //tag=0/1�ֱ��ʾѧ������ʦ����int��������չ�������
	BorrowItemDB borrowItemDB; //���鿨ʵ�����Ǹ��˰�ĵǼǲ�
	int unreturnNum; //δ��ͼ�������

	Reader(String i, String n, int t,int max){
		name=n; ID=i; tag=t; unreturnNum=0;
		borrowItemDB=new BorrowItemDB(max); //Ĭ�Ͻ��鿨����Ϊmax
	}
	String getName(){ return name; }
	void setName(String s){ name=s; }
	String getID(){return ID;}
	void setID(String s){ID=s;}
	int getTag(){return tag;}
	void setTag(int x){tag=x;}
	int getUnreturnNum(){return unreturnNum;}
	void setUnreturnNum(int x){unreturnNum=x;}
	void addBorrowItem(BorrowItem j){	borrowItemDB.add(j);	}
	BorrowItem getBorrowItem(int pos){//����posλ�õĽ�����Ŀ
		return borrowItemDB.getBorrowItem(pos);
	}
	String tagToString(){ //��tagת���ɶ�Ӧ������
		if(tag==0)return "ѧ��";
		if(tag==1)return "��ʦ";
		return "δ֪";
	}
	public String toString(){
		return BookMisFormat.readerID(ID)
			+BookMisFormat.readerName(name)
			+BookMisFormat.readerCategory(this.tagToString());
	}
	void showBorrowItemDBInfo(){ //�����ġ�δ����¼��ȫ��������Ϣ
		borrowItemDB.showInfo();
	}
	void showUnreturn(){ //��ʾ����δ�����¼
		borrowItemDB.showUnreturn();
	}
}
/*���߿�ReaderDB�������ͼ���BookDB��������
 *getReader(id)�����顢����ʱ����Ҫ�ṩ���ߵ�ID�ҵ����ߣ������ҵ���صĽ��鿨
 **/
class ReaderDB  implements Serializable{//���߿⡤�洢���ж��ߵ���Ϣ
	final int maxNum; //Ĭ�϶��ߵ��������
	int length;//���ߵ�ʵ������
	Reader[] db;

	ReaderDB(int max){ maxNum=max; db=new Reader[max]; }
	void showInfo(){//��ʾ���ж�����Ϣ
		System.out.print(BookMisFormat.getReaderTitle());
		for(int i=0; i<length; i++)
			System.out.println(db[i]);
	}
	Reader getReader(String id){//���ݶ��߱�ŷ��ض�Ӧ�Ķ��߶���
		for(int i=0; i<length; i++)
			if(db[i].getID().equals(id))return db[i];
		return null;
	}
	static ReaderDB readReaderDBFromFile(String fileName, int max)throws Exception{
		//���ı��ļ���ȡ���ж�����Ϣ
		ReaderDB readerDB=new ReaderDB(max);
		String id,name; int tag;//���
		Scanner sc=new Scanner(new File(fileName));
		sc.nextLine();		sc.nextLine(); //fpǰ���в�������
		Reader reader; int i;
		for(i=0;sc.hasNext(); i++){
			id=sc.next(); name=sc.next(); tag=sc.nextInt();
			reader=new Reader(id,name,tag,100);//�ɴ洢100�������¼
			readerDB.db[i]=reader;
		}
		readerDB.length=i; //��¼��ȡ���ߵ�����
		return readerDB;
	}
}
/*������Ŀ��һ��������Ŀ����һ�����ļ�¼��Ҳ�Ƕ��߽��鿨���Ǽǲ��������Ŀ
 *������Ϣ�����ߡ�ͼ�顢���ĺͻ������ڡ�
 **/
class BorrowItem implements Serializable{//������Ŀ������͵Ǽ�ʱ��Ҫ��д��
	Book book;
	Reader reader;
	LocalDate borrowDate,returnDate;
	BorrowItem(Reader r,Book b){//���캯��ֻ��Խ��ģ�������޸Ļ�������
		book=b; reader=r; borrowDate=LocalDate.now();
	}
	Reader getReader(){ return reader; }
	void setReader(Reader r){ reader=r; }
	Book getBook(){ return book; }
	void setBook(Book b){ book=b; }

	LocalDate getborrowDate(){ return borrowDate; }
	void setBorrowDate(LocalDate d){ borrowDate=d; }
	LocalDate getReturnDate(){ return returnDate; }
	void setReturnDate(LocalDate d){ returnDate=d; }

	public String toString(){
		//���ļ�¼����ˮ�š���� ���� ��� ���� ���� ������ ISBN ��������  ��������
		return BookMisFormat.readerID(reader.getID())
			+BookMisFormat.readerName(reader.getName())
			+BookMisFormat.readerCategory(reader.tagToString())
			+BookMisFormat.bookTitle(book.getTitle())
			+BookMisFormat.author(book.getAuthor())
			+BookMisFormat.press(book.getPress())
			+BookMisFormat.ISBN(book.getISBN())
			+BookMisFormat.date(borrowDate)
			+BookMisFormat.date(returnDate);
	}
}
/*������Ŀ�⣺˳���洢
 **/
class BorrowItemDB implements Serializable{//������Ŀ��
	//����ʱ�洢������Ŀ��λ�ã��Ա㻹��
	final int maxNum;
	BorrowItem[] db;
	int length;//ͼ���ʵ�����࣬��ͬ��ISBN������5������5������һ��
	BorrowItemDB(int max){
		maxNum=max; db=new BorrowItem[max]; length=0;
	}
	void showInfo(){//��ʾ���н��ļ�¼
		System.out.print(BookMisFormat.getBorrowTitle());
		for(int i=0; i<length; i++)
			System.out.println(BookMisFormat.position(i)+" "+db[i]);
	}
	void add(BorrowItem j){//���ӽ��ļ�¼��ע��һ�㲻��ɾ�����ļ�¼
		if(length>=maxNum){
			System.out.print("��������������"); return;
		}
		db[length]=j; length++;
	}
	BorrowItem getBorrowItem(int pos){//����posλ�õĽ�����Ŀ
		if(pos<0||pos>=length){
			System.out.println("�޴���Ŀ��");return null;
		}
		return db[pos];
	}
	void showUnreturn(){ //��ʾ����δ�����¼
		System.out.print(BookMisFormat.getBorrowTitle());
		for(int i=0; i<length; i++)
			if(db[i].getReturnDate()==null)
				 System.out.println(BookMisFormat.position(i)+" "+db[i]);
	}

}
class BusinessLogic{//ҵ���߼������ֹ�����Ϊ
	static final int[] maxUnreturnBookNum={2,4};
		//��tag=0�Ķ������δ��������2����tag=1�Ķ�������δ��4��
	static boolean isFitRules(Reader reader, Book book){
		if(book==null){
			System.out.println("\nͼ�鲻���ڣ�"); return false;
		}
		if(book.getUnreturnNum()==0){
			System.out.println("\nͼ�鵱ǰ�޿�棡"); return false;
		}
		if(reader.getUnreturnNum()>=maxUnreturnBookNum[reader.getTag()]){
			System.out.println("\n�Ѵ�����޶���ܽ��ģ�"); return false;
		}
		return true;
	}
	static boolean borrowBooks(Reader reader, Book book, BorrowItemDB borrowItemDB){
		if(isFitRules(reader,book)==false) return false;    //��������������ֱ�ӷ���
		BorrowItem borrowItem=new BorrowItem(reader,book);  //1�����ɽ�����Ŀ
		borrowItemDB.add(borrowItem);                       //2����д�Ǽǲ�
		reader.addBorrowItem(borrowItem);                   //3����д���ߵĽ��鿨
		reader.setUnreturnNum(reader.getUnreturnNum()+1);   //4���޸Ķ���δ������
		book.setUnreturnNum( book.getUnreturnNum()-1 );     //5���޸Ŀ�浱ǰ��
		book.setborrowCount( book.getborrowCount()+1 );     //6���޸ı���Ľ��Ĵ���
		System.out.println(book.getTitle()+" ���ĳɹ���");
		return true;
	}
	static void returnBooks(Reader reader, int pos){//�����ṩ�Լ����鿨����ˮ��������
		BorrowItem jt=reader.getBorrowItem(pos);  //ͨ��ͼ��λ�úŶ�λ���߽��鿨�еĽ�����Ŀ
		jt.setReturnDate(LocalDate.now()); //��д������Ŀ�еĻ�������
		Book b=jt.getBook();                //ͨ��������Ŀ�ҵ���Ӧ���鼮
		reader.setUnreturnNum(reader.getUnreturnNum()-1);   //�޸Ķ���δ������
		b.setUnreturnNum( b.getUnreturnNum()+1);//�޸Ŀ�浱ǰ��
		System.out.println(b.getTitle()+" ����ɹ���");
	}
}
class BookMisApp implements Serializable{
	static final String bookDBFileName="bookDB.txt";
	static final String readerDBFileName="readerDB.txt";
	static final String sysObjFileName="bookMisObj.dat";
	BookDB bookDB; //ϵͳ�õ�ͼ���
	BorrowItemDB borrowItemDB;  //ϵͳ�õĵǼǲ�
	ReaderDB readerDB;  //ϵͳ�õĶ��߿�

	int showMenu(){
		Scanner sc=new Scanner(System.in);
		int x=-1;
		while(x<0||x>8&&x!=1234){
			System.out.println("******************************");
			System.out.println("      ͼ�����ϵͳ�˵�    ");
			System.out.println("******************************");
			System.out.println("     1. ��ѯͼ��");
			System.out.println("     2. ����");
			System.out.println("     3. ����");
			System.out.println("     4. ��ʾ����ͼ��");
			System.out.println("     5. ��ʾ���ж�����Ϣ");
			System.out.println("     6. ��ʾ�Ǽǲ�ȫ����Ϣ");
			System.out.println("     7. ��ʾ���߽�����Ϣ");
			System.out.println("     8. ���浱ǰ����\n");
			System.out.println("     0. �˳�ϵͳ");
			System.out.println("  1234. ��ʼ��ϵͳ����\n");
			System.out.println("------------------------------");
			System.out.print("��ѡ��");
			x=sc.nextInt();
		}
		return x;
	}
	void execute(int x)throws Exception{
		Scanner sc=new Scanner(System.in);
		if(x==1234){ init(); return;}
		if(x==1){ //��ѯͼ��
			System.out.print("�����������а��������֣� ");
			String s=sc.next();
			System.out.println("��������ѯ������¡���������");
			bookDB.find(s);
			return;
		}
		if(x==2){ //����
			System.out.print("�����顿���������ID��ͼ�����ˮ�ţ�");
			String id=sc.next();
			Reader reader=readerDB.getReader(id);
			if(reader==null){
				System.out.println("���߲����ڣ�"); return;
			}
			int pos=sc.nextInt();
			Book b=bookDB.getBook(pos);
			if(b==null){
				System.out.println("ͼ�鲻���ڣ�"); return;
			}
			boolean jg=BusinessLogic.borrowBooks(reader,b,borrowItemDB );
			if(jg==true)
				System.out.println("����ɹ�����ǰδ����¼Ϊ��");
			reader.showUnreturn();
			return;
		}
		if(x==3){ //����
			System.out.print("���������ID�� ");
			String id=sc.next();
			Reader reader=readerDB.getReader(id);
			if(reader==null){
				System.out.println("���߲����ڣ�"); return;
			}
			System.out.println("���� "+reader.getName()+" ��δ���鼮���£�");
			reader.showUnreturn();
			System.out.print("������Ҫ��ͼ�����ˮ�ţ� ");
			int pos=sc.nextInt();
			BusinessLogic.returnBooks(reader,pos);
			System.out.println("����ɹ�����ǰδ����¼Ϊ��");
			reader.showUnreturn();
			return;
		}
		if(x==4){ bookDB.showInfo(); return;}//��ʾ����ͼ��
		if(x==5){ readerDB.showInfo(); return;}//��ʾ���ж�����Ϣ
		if(x==6){ borrowItemDB.showInfo(); return;}//��ʾ�Ǽǲ�ȫ����Ϣ
		if(x==7){ //��ʾ���߽�����Ϣ
			System.out.print("���������ID�� ");
			String id=sc.next();
			Reader reader=readerDB.getReader(id);
			if(reader==null){
				System.out.println("���߲����ڣ�"); return;
			}
			reader.showBorrowItemDBInfo();
			return;
		}
		if(x==8){ //���浱ǰ����
			writeAllData( sysObjFileName );
			System.out.println("�����ѱ��棡");
			return;
		}
		if(x==0){  //�˳�ϵͳ
			writeAllData( sysObjFileName ); //�ȱ����������ݣ���ϵͳ����
			System.exit(0);
		}
	}
	void writeAllData(String fileName) throws Exception{ //д��ͼ�����ݿ����
		//������static����Ϊֻ�е�ϵͳ�������ʱ������д����
		FileOutputStream fo=new FileOutputStream(fileName);
		ObjectOutputStream obj_o=new ObjectOutputStream(fo);
		obj_o.writeObject(this);  //������ϵͳ���󣨼�BookMisApp��д���ļ�
		obj_o.close();
	}
/*���ڶ������л��ļ򵥽��ͣ�������IO���½���ϸ������
 *������ܹ�ϵ�Ƚϸ��ӣ��������ֻ����ͷ���д���ļ���δ����������㣩��ʵ�������岻��
 *�������л����ƣ�ͨ�׵ؽ�������һ�֡�˳�����ϡ����ƣ�
 *   �������л����ƽ������ͷ���д���ļ�����ӱ�ͷ��ʼ˳�����ϣ����������е����н���д�롣
 *   ���Ƶأ���д�����������е����н����ᱻ�Զ�д���ļ���
 *     ��ʹ�����ֻ�����Ҫ��Ҫ��д�Ķ���������Ķ��󣬱��붼Ҫʵ�ֽӿ�java.io.Serializable��
 *     ����һ���սӿڣ����𵽱�־���á�
 *  �籾��Ҫд��BookMisApp�Ͷ��󣬸ö����漰BookDB��ReaderDB��BorrowItemDB�Ͷ���
 *    ��Щ�������漰Book��Reader��BorrowItem�ȶ���
 *    ������Ϊʹ�����л����ƣ���Щ���ڶ���ʱ���������" implements Serializable"
 *  д�ļ���ʽ���ļ�-->�����ļ������-->�������������obj_o��֮����obj_o��writeObject()����
 *  ���ļ�������ơ�
*/
	static BookMisApp readAllData(String fileName) throws Exception{//��ȡͼ�����ݿ����
		//��static������Ϊ���ļ��ж�ȡ���ݺ󣬻�����µĶ��߿����
		FileInputStream fi=new FileInputStream(fileName);
		ObjectInputStream obj_i=new ObjectInputStream(fi);
		BookMisApp sysObj=( BookMisApp )obj_i.readObject();//���ļ��лָ�ϵͳ����
		obj_i.close();
		return sysObj;
	}
	static BookMisApp init()throws Exception{//��ʼ��
	  //��Ҫ�������£���յǼǲᡢ��������ͼ����Ϣ�Ͷ�����Ϣ������ϵͳ����
		BookMisApp sysObj=new BookMisApp();
		sysObj.borrowItemDB=new BorrowItemDB(1000);//�Ǽǲ�
		System.out.println("\n�Ǽǲ�����գ�");
		sysObj.bookDB=BookDB.readBookDBFromFile( bookDBFileName, 100 );//����ͼ��
		sysObj.readerDB=ReaderDB.readReaderDBFromFile( readerDBFileName,100);// �������
		System.out.println("\n�鼮��Ϣ���£�");
		sysObj.bookDB.showInfo();
		System.out.println("\n������Ϣ���£�");
		sysObj.readerDB.showInfo();
		return sysObj;
	}
	public static void main (String[] args)throws Exception {
		BookMisFormat.init();
		File f=new File(sysObjFileName);
		BookMisApp sysObj;
		if(!f.exists()) sysObj=init();  //��������ļ������ڣ��ͳ�ʼ��
		else sysObj=BookMisApp.readAllData( sysObjFileName );
		//ע�����Գ�������ԡ����������޸ģ�������ɾ�����ɵ�dat�ļ����������Զ���ʼ����
		//�����ȡ�Ķ�����Ϣ�뵱ǰ�Ķ�����Ϣ��һ�£������������
		int select;
		Scanner sc=new Scanner(System.in);
		while( true ){//ѭ����ʾ�˵�
			select=sysObj.showMenu();
			sysObj.execute(select);
			System.out.println("\n=====������ǿհ��ַ�+�س� ����===");
			sc.next(); //������������ͣ
		}
	}
}