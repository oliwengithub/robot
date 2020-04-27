echo "start kill jave process"
jarname=`ls|grep *.jar`
echo jar name is $jarname
pid=`ps -ef| grep $jarname | grep -v grep |  awk '{print $2}'`
if [ `echo $pid | wc -L` -gt 0 ] ; then 
	echo "进程号: " $pid
	kill $pid
	echo "进程号: " pid " 已经杀死" 
else
	echo "java进程不存在"
fi
