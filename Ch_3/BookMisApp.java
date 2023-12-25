/*功能需求：
 *某图书室负责向本院教师、学生提供图书借阅服务。图书室有一本表格形式的登记册，
 *表格栏目包括读者编号（学号或者教号）、姓名、书名、ISBN、借阅日期、还书日期。
 *每个读者有一张借书卡，记录个人借阅情况（书名、ISBN、借阅日期、还书日期）。
 *不同类型人员（教师/学生）有不同的借阅数量限制，
 *如每位学生/教师未还图书至多2/4本，否则将不允许借书。
 *借书时，要填写读者的借书卡和图书室的登记册，还书时则在借书卡、登记册相关条目上填写还书日期。
 *还书后，图书室管理员会在登记册及读者借书卡的对应条目上盖章。
 **/
import java.io.File;
import java.util.Scanner;
import java.time.LocalDate;
//下面是用于序列化的类、接口，现阶段读者可不必理会
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
/*字符串工具类StringFormat
 *特色：可用于包含中文字符的字符串进行格式化输出
 **/
class StringFormat{//自定义格式化字符串，用于格式化包含汉字的字符串
    static boolean isChinese(char c) {//识别是否为汉字
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION)
            return true;
        return false;
    }
	static int length(String s){//s的实际占用宽度：一个汉字占两个字符位置
		char[] chAr = s.toCharArray();
		int c=0;
		for(char x: chAr)
			c=(isChinese(x))?c+2:c+1;
		return c;
	}
	static String stringHead(String s, int len){//取s中从头开始的len个字符宽度（注：中文占2个字符）
		char[] chAr = s.toCharArray();
		int c=0,i=0;
		for( ; i<chAr.length&&c<len; i++)//当c满足宽度要求时，i就是所取字符的实际数量
			c=(isChinese(chAr[i]))?c+2:c+1;//c用于计算实际宽度
		return s.substring(0,i);//substring(0,i)源自String，从头取i个字符
	}
	static String repeat(char c, int n){//将字符c重复n次
		if(n<=0) return "";
		String s="";
		for(int i=0; i<n; i++)s=s+c;
		return s;
	}
	static String formatR(String s, int n, char c ){//右对齐：将s（可能含有汉字）补成长度为n的字符串，c是填充字符，补充在左部
		int len=length(s);
		if(len>=n) return stringHead(s,n);
		return repeat(c,n-len)+s;
	}
	static String formatL(String s, int n, char c ){//左对齐：将s（可能含有汉字）补成长度为n的字符串，c是填充字符，补充在右部
		int len=length(s);
		if(len>=n) return stringHead(s,n);
		return s+repeat(c,n-len);
	}
	static String formatC(String s, int n, char c ){//居中对齐：将s（可能含有汉字）补成长度为n的字符串，c是填充字符，补充在右部
		int left,right,len=length(s);
		if(len>=n) return stringHead(s,n);
		left=(n-len)/2; right=n-len-left;
		return repeat(c,left)+s+repeat(c,right);
	}
}
/*图书格式化类BookMisFormat：用于本系统中所有需要输出的术语，均在此格式化
 *所谓格式化，就是返回指定宽度和对齐方式的字符串。
 *如图书书名格式化，就是将书名转变成固定长度、固定对齐方式的字符串
 *另外，还包括图书、读者、借阅条目信息输出的标题行
 **/
class BookMisFormat{//对本系统中的所有数据的格式化输出，包括输出的标题行
	//定义图书、读者、借阅记录显示时的标题行字符串
	static String bookItemTitleStr,readerItemTitleStr,borrowItemTitleStr;
	//定义各类标识占用的宽度
	static int numWidth,authorWidth,bookTitleWidth,pressWidth,
	    isbnWidth,dateWidth,readerIDWidth,readerNameWidth,readerCategoryWidth;
	static String getBookTitle(){  return bookItemTitleStr; }
	static String getReaderTitle(){ return readerItemTitleStr; }
	static String getBorrowTitle(){ return borrowItemTitleStr; }
	//将属性按指定宽度对齐
	static void setWidth(int wz,int sm,int zz, int cbs, int isbn, int rq, int sl,int id, int xm, int lb){
		numWidth=wz; bookTitleWidth=sm; authorWidth=zz; pressWidth=cbs; isbnWidth=isbn; dateWidth=rq; numWidth=sl;
		readerIDWidth=id; readerNameWidth=xm; readerCategoryWidth=lb;
		//图书信息标题行bookItemHeadInfo：位置、书名、作者、出版社、出版日期、ISBN、总量、在库、借阅
		//读者信息标题行readerItemHeadInfo：编号、姓名、类别
		//借阅记录标题行borrowItemHeadInfo：编号 姓名 类别 书名 作者 出版社 ISBN 借阅日期  还书日期
	}
	static String position(int pos){ return String.format("%0"+numWidth+"d",pos); }//流水号：图书在书库中的下标
	static String bookTitle(String sm){ return StringFormat.formatL(sm,bookTitleWidth,' '); }
	static String author(String zz){ return StringFormat.formatL(zz,authorWidth,' '); }
	static String press(String cbs){ return StringFormat.formatL(cbs,pressWidth,' '); }
	static String ISBN(String i){ return StringFormat.formatL(i,isbnWidth,' '); }
	static String date(LocalDate rq){
		if(rq==null)return StringFormat.formatC("未知",dateWidth,' ');
		else return StringFormat.formatL(rq+"",dateWidth,' '); }
	static String num(int sL){ return StringFormat.formatC(sL+"",numWidth,' '); }
	static String readerID(String id){ return StringFormat.formatL(id,readerIDWidth,' '); }
	static String readerName(String xm){ return StringFormat.formatL(xm,readerNameWidth,' '); }
	static String readerCategory(String lb){ return StringFormat.formatL(lb,readerCategoryWidth,' '); }
	static void creatAllHeadLineStr(){
		/*图书信息标题行显示如下：
		 * 流水号、书名、作者、出版社、出版日期、ISBN、总量、在库、借阅
		 *--------------------------------------------------------------
		 **/
		bookItemTitleStr=StringFormat.formatC("流水号",numWidth,' ')+StringFormat.formatC("书名",bookTitleWidth,' ')
			+StringFormat.formatC("作者",authorWidth,' ')+StringFormat.formatC("出版社",pressWidth,' ')
			+StringFormat.formatC("出版日期",dateWidth,' ')+StringFormat.formatC("ISBN",isbnWidth,' ')
			+StringFormat.formatC("总量",numWidth,' ')+StringFormat.formatC("在库",numWidth,' ')
			+StringFormat.formatC("借阅",numWidth,' ')+"\n";
		//计算图书标题行的宽度，下面各项次序与标题行的各项宽度对应
		int bookItemHeadInfoWidth=numWidth+bookTitleWidth+authorWidth+pressWidth
			  +dateWidth+isbnWidth+numWidth+numWidth+numWidth;
		bookItemTitleStr=bookItemTitleStr+StringFormat.repeat('-',bookItemHeadInfoWidth)+"\n";
		/*读者信息标题行显示如下：
		 * 编号 姓名 性别
		 *----------------
		 **/
		readerItemTitleStr=StringFormat.formatC("编号",readerIDWidth,' ')
			+StringFormat.formatC("姓名",readerNameWidth,' ')+StringFormat.formatC("类别",readerCategoryWidth,' ')+"\n";
		int readerItemTitleStrWidth=readerIDWidth+readerNameWidth+readerCategoryWidth;
		readerItemTitleStr=readerItemTitleStr+StringFormat.repeat('-',readerItemTitleStrWidth)+"\n";
		/*借阅信息标题行显示如下：
		 * 流水号 编号 姓名 类别 书名 作者 出版社 ISBN 借阅日期  还书日期
		 *----------------------------------------------------------------
		 **/
		borrowItemTitleStr=StringFormat.formatC("流水号",numWidth,' ')+StringFormat.formatC("编号",readerIDWidth,' ')
			+StringFormat.formatC("姓名",readerNameWidth,' ')+StringFormat.formatC("类别",readerCategoryWidth,' ')
			+StringFormat.formatC("书名",bookTitleWidth,' ')+StringFormat.formatC("作者",authorWidth,' ')
			+StringFormat.formatC("出版社",pressWidth,' ')+StringFormat.formatC("ISBN",isbnWidth,' ')
			+StringFormat.formatC("借阅日期",dateWidth,' ')+StringFormat.formatC("还书日期",dateWidth,' ')+"\n";
		int borrowItemTitleStrWidth=numWidth+readerIDWidth+readerNameWidth+readerCategoryWidth
			   +bookTitleWidth+authorWidth+pressWidth+isbnWidth+dateWidth+dateWidth;
		borrowItemTitleStr=borrowItemTitleStr+StringFormat.repeat('-',borrowItemTitleStrWidth)+"\n";
	}
	static void init(){//初始化
		setWidth(6,32,14,20,14,11,5,13,10,4); //注意，要比各项的实际宽度略大，以免挤在一起
			//参数对应次序为：流水号、书名、作者、出版社、ISBN、日期、数量、读者编号、读者姓名（不同于作者）、读者类别
			//流水号就是图书在书库中的位置编号
		creatAllHeadLineStr();//创建各标题行
	}
}
/*图书类·单本图书应具备的基本信息和操作
 *涉及基本信息包括：书名、作者、出版社、ISBN、出版日期等基本信息
 *另外，考虑到借阅，引入了（某一本）图书总量、在库数量，以及借阅次数（方便统计最受欢迎的图书）
 *为简化设计，将图书信息的输出设计为固定，见toString()
 **/
class Book implements Serializable{
	String title,author,press,ISBN;//书名、作者、出版社、ISBN
	LocalDate publicationDate;     //出版日期
	int total,unreturnNum,borrowCount;  //图书的总量、在库数量、借阅次数
	Book(String sm,String zz,String cbs,LocalDate cbrq,String isbn,int zs, int dqs, int jycs){
	 //注意：鉴于Scanner默认用空格作为分隔符，因此书名等字符串不能出现空格，否则读取文件将出错
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
		//格式：书名 作者 出版社 出版日期 ISBN 总量 在库数量 借阅次数
		return BookMisFormat.bookTitle(title)+BookMisFormat.author(author)
			+BookMisFormat.press(press)+BookMisFormat.date(publicationDate)
			+BookMisFormat.ISBN(ISBN)+BookMisFormat.num(total)
			+BookMisFormat.num(unreturnNum)+BookMisFormat.num(borrowCount);
	}
}
/*图书库类BookDB：用顺序表组织图书
**/
class BookDB implements Serializable{
	final int maxNum; //默认图书的最大容量
	int length;//图书的实际种类
	Book[] db;
	BookDB(int max){ maxNum=max; db=new Book[max]; }
	int find(Book b){//根据图书中的ISBN查找，结果具有唯一性
		//为方便利用查询，这里返回位置信息，-1表示不存在
		if(b==null)return -1;
		for(int i=0; i<length; i++)
			if(db[i].getISBN().equals(b.getISBN())) return i;
		return -1;
	}
	void find(String bookName){//根据书名字符串查找，由于模糊查找可能有一组记录，为简单起见，这里直接输出查找结果。
		//显示所有包含字符串s的记录
		System.out.print(BookMisFormat.getBookTitle());
		boolean flag=false;
		for( int i=0; i<length; i++)
			if(db[i].getTitle().indexOf(bookName)>=0){//若找到匹配的图书
				flag=true;
				System.out.println(BookMisFormat.position(i)+" "+db[i]);}
		if(flag==false)
			System.out.println("----没找到任何匹配的图书-----");
	}
	void add(Book b){//新增的图书数量已放置在b中
		if(length==maxNum) {
			System.out.println("\n书库容量已满，请更改容量后再添加图书！");
			return ;
		}
		int pos=find(b);//添加前先看图书是否存在，注：ISBN是图书的唯一标识
		if(pos==-1){ //图书不存在
			db[length]=b; length++;
			return;
		}
		if(pos>=0){ //图书已存在，更新数量及库存，但不能修改表长
			db[pos].setTotal( b.getTotal()+db[pos].getTotal() );
			db[pos].setUnreturnNum( b.getTotal()+db[pos].getUnreturnNum());
		}
	}
	Book getBook(int pos){//获取pos位置上的图书
		if(pos<0||pos>=length)return null;
		return db[pos];
	}
	void showInfo(){//显示所有图书信息
		System.out.print(BookMisFormat.getBookTitle());
		for(int i=0; i<length; i++)
			System.out.println(BookMisFormat.position(i)+" "+db[i]);
	}
	void showInfo(int pos){ //显示下标为pos的图书信息
		if(pos<0||pos>=length)return;
		System.out.print(BookMisFormat.getBookTitle());
		System.out.println(BookMisFormat.position(pos)+" "+db[pos]);
	}
	static BookDB readBookDBFromFile(String fileName, int max)throws Exception{//从文本文件读取所有图书信息
		BookDB bookDB =new BookDB(max);
		String bookTitle,author,press,ISBN,cbrq;
		LocalDate publicationDate;
		int total,unreturnNum,borrowCount;
		Scanner sc=new Scanner(new File(fileName));
		sc.nextLine();		sc.nextLine(); //文件的前两行是标题行，不是数据
		Book b; int i;
		for(i=0;sc.hasNext(); i++){ //注：hasNext()、hasNextLine()不会移动读取的指针，仅判断 sc.hasNextLine()&&
		//另外，有时hasNextLine()为真时，后面不一定有数据，如空行等，由于本循环的第一条语句是sc.next()，因此加入了hasNext()
			bookTitle=sc.next(); author=sc.next(); press=sc.next();
			cbrq=sc.next();  publicationDate=LocalDate.parse(cbrq);  ISBN=sc.next();
			total=sc.nextInt();   unreturnNum=sc.nextInt();   borrowCount=sc.nextInt();
			b= new Book(bookTitle,author,press,publicationDate,ISBN,total,unreturnNum,borrowCount);
			bookDB.db[i]=b;
		}
		bookDB.length=i; //记录读取图书的数量
		return bookDB;
	}
}
/*读者类Reader
 *  基本信息：编号、姓名、类别（0/1：学生/教师）、借书卡（顺序表）
 *    辅助信息：借书卡容量、表长、未还书的数量、
 *  基本操作：对上述属性的get/set
 * 【说明】
 *1、读者的借书卡存储的是自己的借阅信息，登记册中存储的是所有读者的借阅信息。
 *   注意：借书卡、登记册中，关联的实际上是同一个借阅条目对象
 *     这样，在还书时，只需修改借阅条目，不涉及与该条目关联的借书卡、登记册；
 *2、为方便还书，增加两个操作：
 *   a. showUnreturn()：显示该读者的所有未还记录
 *   b. getBorrowItem(pos)：返回读者借书卡中pos位置的借阅条目
 **/
class Reader implements Serializable{//读者:姓名、编号、类别、关联借书卡
	String name,ID;
	int tag; //tag=0/1分别表示学生、教师，用int型易于拓展更多分类
	BorrowItemDB borrowItemDB; //借书卡实际上是个人版的登记册
	int unreturnNum; //未还图书的数量

	Reader(String i, String n, int t,int max){
		name=n; ID=i; tag=t; unreturnNum=0;
		borrowItemDB=new BorrowItemDB(max); //默认借书卡容量为max
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
	BorrowItem getBorrowItem(int pos){//返回pos位置的借阅条目
		return borrowItemDB.getBorrowItem(pos);
	}
	String tagToString(){ //将tag转换成对应的名称
		if(tag==0)return "学生";
		if(tag==1)return "教师";
		return "未知";
	}
	public String toString(){
		return BookMisFormat.readerID(ID)
			+BookMisFormat.readerName(name)
			+BookMisFormat.readerCategory(this.tagToString());
	}
	void showBorrowItemDBInfo(){ //含借阅、未还记录的全部借阅信息
		borrowItemDB.showInfo();
	}
	void showUnreturn(){ //显示所有未还书记录
		borrowItemDB.showUnreturn();
	}
}
/*读者库ReaderDB：设计与图书库BookDB基本类似
 *getReader(id)：借书、还书时，需要提供读者的ID找到读者，进而找到相关的借书卡
 **/
class ReaderDB  implements Serializable{//读者库·存储所有读者的信息
	final int maxNum; //默认读者的最大容量
	int length;//读者的实际数量
	Reader[] db;

	ReaderDB(int max){ maxNum=max; db=new Reader[max]; }
	void showInfo(){//显示所有读者信息
		System.out.print(BookMisFormat.getReaderTitle());
		for(int i=0; i<length; i++)
			System.out.println(db[i]);
	}
	Reader getReader(String id){//根据读者编号返回对应的读者对象
		for(int i=0; i<length; i++)
			if(db[i].getID().equals(id))return db[i];
		return null;
	}
	static ReaderDB readReaderDBFromFile(String fileName, int max)throws Exception{
		//从文本文件读取所有读者信息
		ReaderDB readerDB=new ReaderDB(max);
		String id,name; int tag;//类别
		Scanner sc=new Scanner(new File(fileName));
		sc.nextLine();		sc.nextLine(); //fp前两行不是数据
		Reader reader; int i;
		for(i=0;sc.hasNext(); i++){
			id=sc.next(); name=sc.next(); tag=sc.nextInt();
			reader=new Reader(id,name,tag,100);//可存储100条借书记录
			readerDB.db[i]=reader;
		}
		readerDB.length=i; //记录读取读者的数量
		return readerDB;
	}
}
/*借书条目：一个借阅条目就是一条借阅记录，也是读者借书卡、登记册关联的条目
 *基本信息：读者、图书、借阅和还书日期、
 **/
class BorrowItem implements Serializable{//借阅条目：借书和登记时需要填写的
	Book book;
	Reader reader;
	LocalDate borrowDate,returnDate;
	BorrowItem(Reader r,Book b){//构造函数只针对借阅，还书仅修改还书日期
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
		//借阅记录：流水号、编号 姓名 类别 书名 作者 出版社 ISBN 借阅日期  还书日期
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
/*借阅条目库：顺序表存储
 **/
class BorrowItemDB implements Serializable{//借阅条目库
	//借阅时存储借阅条目的位置，以便还书
	final int maxNum;
	BorrowItem[] db;
	int length;//图书的实际种类，如同样ISBN的书有5本，这5本算作一类
	BorrowItemDB(int max){
		maxNum=max; db=new BorrowItem[max]; length=0;
	}
	void showInfo(){//显示所有借阅记录
		System.out.print(BookMisFormat.getBorrowTitle());
		for(int i=0; i<length; i++)
			System.out.println(BookMisFormat.position(i)+" "+db[i]);
	}
	void add(BorrowItem j){//增加借阅记录【注】一般不能删除借阅记录
		if(length>=maxNum){
			System.out.print("借阅容量已满。"); return;
		}
		db[length]=j; length++;
	}
	BorrowItem getBorrowItem(int pos){//返回pos位置的借阅条目
		if(pos<0||pos>=length){
			System.out.println("无此条目！");return null;
		}
		return db[pos];
	}
	void showUnreturn(){ //显示所有未还书记录
		System.out.print(BookMisFormat.getBorrowTitle());
		for(int i=0; i<length; i++)
			if(db[i].getReturnDate()==null)
				 System.out.println(BookMisFormat.position(i)+" "+db[i]);
	}

}
class BusinessLogic{//业务逻辑：各种功能行为
	static final int[] maxUnreturnBookNum={2,4};
		//即tag=0的读者最多未还的数量2本，tag=1的读者至多未还4本
	static boolean isFitRules(Reader reader, Book book){
		if(book==null){
			System.out.println("\n图书不存在！"); return false;
		}
		if(book.getUnreturnNum()==0){
			System.out.println("\n图书当前无库存！"); return false;
		}
		if(reader.getUnreturnNum()>=maxUnreturnBookNum[reader.getTag()]){
			System.out.println("\n已达最大限额，不能借阅！"); return false;
		}
		return true;
	}
	static boolean borrowBooks(Reader reader, Book book, BorrowItemDB borrowItemDB){
		if(isFitRules(reader,book)==false) return false;    //若不满足条件则直接返回
		BorrowItem borrowItem=new BorrowItem(reader,book);  //1、生成借阅条目
		borrowItemDB.add(borrowItem);                       //2、填写登记册
		reader.addBorrowItem(borrowItem);                   //3、填写读者的借书卡
		reader.setUnreturnNum(reader.getUnreturnNum()+1);   //4、修改读者未还数量
		book.setUnreturnNum( book.getUnreturnNum()-1 );     //5、修改库存当前数
		book.setborrowCount( book.getborrowCount()+1 );     //6、修改本书的借阅次数
		System.out.println(book.getTitle()+" 借阅成功！");
		return true;
	}
	static void returnBooks(Reader reader, int pos){//读者提供自己借书卡的流水号来还书
		BorrowItem jt=reader.getBorrowItem(pos);  //通过图书位置号定位读者借书卡中的借阅条目
		jt.setReturnDate(LocalDate.now()); //填写借阅条目中的还书日期
		Book b=jt.getBook();                //通过借阅条目找到对应的书籍
		reader.setUnreturnNum(reader.getUnreturnNum()-1);   //修改读者未还数量
		b.setUnreturnNum( b.getUnreturnNum()+1);//修改库存当前数
		System.out.println(b.getTitle()+" 还书成功！");
	}
}
class BookMisApp implements Serializable{
	static final String bookDBFileName="bookDB.txt";
	static final String readerDBFileName="readerDB.txt";
	static final String sysObjFileName="bookMisObj.dat";
	BookDB bookDB; //系统用的图书库
	BorrowItemDB borrowItemDB;  //系统用的登记册
	ReaderDB readerDB;  //系统用的读者库

	int showMenu(){
		Scanner sc=new Scanner(System.in);
		int x=-1;
		while(x<0||x>8&&x!=1234){
			System.out.println("******************************");
			System.out.println("      图书借阅系统菜单    ");
			System.out.println("******************************");
			System.out.println("     1. 查询图书");
			System.out.println("     2. 借书");
			System.out.println("     3. 还书");
			System.out.println("     4. 显示所有图书");
			System.out.println("     5. 显示所有读者信息");
			System.out.println("     6. 显示登记册全部信息");
			System.out.println("     7. 显示读者借阅信息");
			System.out.println("     8. 保存当前数据\n");
			System.out.println("     0. 退出系统");
			System.out.println("  1234. 初始化系统数据\n");
			System.out.println("------------------------------");
			System.out.print("请选择：");
			x=sc.nextInt();
		}
		return x;
	}
	void execute(int x)throws Exception{
		Scanner sc=new Scanner(System.in);
		if(x==1234){ init(); return;}
		if(x==1){ //查询图书
			System.out.print("请输入书命中包含的文字： ");
			String s=sc.next();
			System.out.println("。。。查询结果如下。。。。：");
			bookDB.find(s);
			return;
		}
		if(x==2){ //借书
			System.out.print("【借书】请输入读者ID和图书的流水号：");
			String id=sc.next();
			Reader reader=readerDB.getReader(id);
			if(reader==null){
				System.out.println("读者不存在！"); return;
			}
			int pos=sc.nextInt();
			Book b=bookDB.getBook(pos);
			if(b==null){
				System.out.println("图书不存在！"); return;
			}
			boolean jg=BusinessLogic.borrowBooks(reader,b,borrowItemDB );
			if(jg==true)
				System.out.println("借书成功！当前未还记录为：");
			reader.showUnreturn();
			return;
		}
		if(x==3){ //还书
			System.out.print("请输入读者ID： ");
			String id=sc.next();
			Reader reader=readerDB.getReader(id);
			if(reader==null){
				System.out.println("读者不存在！"); return;
			}
			System.out.println("读者 "+reader.getName()+" 的未还书籍如下：");
			reader.showUnreturn();
			System.out.print("请输入要还图书的流水号： ");
			int pos=sc.nextInt();
			BusinessLogic.returnBooks(reader,pos);
			System.out.println("还书成功！当前未还记录为：");
			reader.showUnreturn();
			return;
		}
		if(x==4){ bookDB.showInfo(); return;}//显示所有图书
		if(x==5){ readerDB.showInfo(); return;}//显示所有读者信息
		if(x==6){ borrowItemDB.showInfo(); return;}//显示登记册全部信息
		if(x==7){ //显示读者借阅信息
			System.out.print("请输入读者ID： ");
			String id=sc.next();
			Reader reader=readerDB.getReader(id);
			if(reader==null){
				System.out.println("读者不存在！"); return;
			}
			reader.showBorrowItemDBInfo();
			return;
		}
		if(x==8){ //保存当前数据
			writeAllData( sysObjFileName );
			System.out.println("数据已保存！");
			return;
		}
		if(x==0){  //退出系统
			writeAllData( sysObjFileName ); //先保存所有数据（即系统对象）
			System.exit(0);
		}
	}
	void writeAllData(String fileName) throws Exception{ //写入图书数据库对象
		//不能用static，因为只有但系统对象存在时，才能写对象
		FileOutputStream fo=new FileOutputStream(fileName);
		ObjectOutputStream obj_o=new ObjectOutputStream(fo);
		obj_o.writeObject(this);  //将整个系统对象（即BookMisApp）写入文件
		obj_o.close();
	}
/*关于对象序列化的简单解释：（将在IO流章节详细阐述）
 *对象可能关系比较复杂，如对链表，只将表头结点写入文件（未考虑其他结点），实际上意义不大。
 *对象序列化机制，通俗地讲，就是一种“顺藤摸瓜”机制，
 *   如用序列化机制将链表表头结点写入文件，会从表头开始顺藤摸瓜，将该链表中的所有结点均写入。
 *   类似地，若写入树根，树中的所有结点均会被自动写入文件。
 *     但使用这种机制有要求：要读写的对象及其关联的对象，必须都要实现接口java.io.Serializable。
 *     这是一个空接口，仅起到标志作用。
 *  如本例要写入BookMisApp型对象，该对象涉及BookDB、ReaderDB、BorrowItemDB型对象，
 *    这些对象又涉及Book、Reader、BorrowItem等对象，
 *    这样，为使用序列化机制，这些类在定义时都必须加上" implements Serializable"
 *  写文件方式：文件-->关联文件输出流-->关联对象输出流obj_o，之后用obj_o的writeObject()即可
 *  读文件与此类似。
*/
	static BookMisApp readAllData(String fileName) throws Exception{//读取图书数据库对象
		//用static，是因为从文件中读取数据后，会产生新的读者库对象
		FileInputStream fi=new FileInputStream(fileName);
		ObjectInputStream obj_i=new ObjectInputStream(fi);
		BookMisApp sysObj=( BookMisApp )obj_i.readObject();//从文件中恢复系统数据
		obj_i.close();
		return sysObj;
	}
	static BookMisApp init()throws Exception{//初始化
	  //主要做三件事：清空登记册、批量导入图书信息和读者信息、返回系统对象
		BookMisApp sysObj=new BookMisApp();
		sysObj.borrowItemDB=new BorrowItemDB(1000);//登记册
		System.out.println("\n登记册已清空！");
		sysObj.bookDB=BookDB.readBookDBFromFile( bookDBFileName, 100 );//导入图书
		sysObj.readerDB=ReaderDB.readReaderDBFromFile( readerDBFileName,100);// 导入读者
		System.out.println("\n书籍信息如下：");
		sysObj.bookDB.showInfo();
		System.out.println("\n读者信息如下：");
		sysObj.readerDB.showInfo();
		return sysObj;
	}
	public static void main (String[] args)throws Exception {
		BookMisFormat.init();
		File f=new File(sysObjFileName);
		BookMisApp sysObj;
		if(!f.exists()) sysObj=init();  //如果对象文件不存在，就初始化
		else sysObj=BookMisApp.readAllData( sysObjFileName );
		//注：若对程序的属性、方法做了修改，【必须删除生成的dat文件，这样会自动初始化】
		//否则读取的对象信息与当前的对象信息不一致，将会产生错误
		int select;
		Scanner sc=new Scanner(System.in);
		while( true ){//循环显示菜单
			select=sysObj.showMenu();
			sysObj.execute(select);
			System.out.println("\n=====按任意非空白字符+回车 返回===");
			sc.next(); //作用类似于暂停
		}
	}
}