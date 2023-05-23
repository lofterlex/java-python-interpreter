# JPython
python interpreter by Java

作者整体的思路应该分为以下几步：

1、读取python文件，然后遇到换行符就认为是一行，将这个一行令牌化。

2、令牌化后进行parseToken操作，将token转为AST抽象语法树。

3、进行Symtable化的编译。

内容与本文的第三部分一致。
https://baijiahao.baidu.com/s?id=1757903538510594725&wfr=spider&for=pc

但是为了让java能够执行，我自己添了一个interpreter包，其中重新写了statemeent和expression。
主函数在Interpreter中。

另外，为了支持read-eval-print loop的交互模式，在main函数中我写了一个REPL（还没有完成）。
