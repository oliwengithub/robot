jarname=`ls|grep *.jar`
dlib="libs"
pid=`ps -ef|grep $jarname|grep -v grep`
if [ `echo $pid | wc -L` -gt 0 ] ; then 
  #验证同jar包名称进程是否存在 
  echo "java 进程 " $jarname " 已经存在"
elif [ ! -d $dlib ];then
  #验证lib包是否存在
  echo $dlib "不存在"
else
  echo "启动中..."
	nohup java -Dloader.path=$dlib -jar $jarname >/dev/null 2>&1 &
	echo "启动完成"
fi
