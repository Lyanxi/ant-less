##To run the ant task,need to rename the build-lessc-task.xml,build-lessc-notask or build-rhino.xml to build.xml.

build-rhino.xml use the third party libs in the directory "libs".

build-lessc-task.xml use the "lessc" command which has already installed in your computer from [lesscss.org](http://lesscss.org).I have used a shellscript file "less.sh" to invoke this command.

build-lessc-notask.xml also use the "lessc" command,but doesn't use an external task in ant.If the error message "Permission Denied..." is occurred,change the permission of the lessc(the value of ${less.tool} in build-lessc-notask.xml) with the command "chmod 755 lessc".

