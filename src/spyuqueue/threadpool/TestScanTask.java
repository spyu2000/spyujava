package spyuqueue.threadpool;

import java.io.File;

public class TestScanTask implements ITask{

	private File file=null;
	private String extName="";
	
	public TestScanTask(File file,String extName){
		this.file=file;
		this.extName=extName;
	}
	@Override
	public void execTask() {
		// TODO Auto-generated method stub
		this.scanDir(this.file,this.extName);
	}
	private void scanDir(File file,String extName){
		if(file==null){
			return;
		}
		if(file.exists()){
			if(file.isFile()){
				if(file.getName().endsWith(extName)){
					System.out.println(file.getAbsolutePath()+File.separator+file.getName());
				}
				return;
			}else{
				File[] files=file.listFiles();
				File tempFile=null;
				if(files!=null){
					for(int i=0;i<files.length;i++){
						tempFile=files[i];
						if(tempFile.isFile()){
							if(tempFile.getName().endsWith(extName)){
								System.out.println(file.getAbsolutePath()+File.separator+tempFile.getName());
							}
						}else if(tempFile.isDirectory()){
							scanDir(tempFile,extName);
						}						
					}
				}				
				
			}
		}
	}

}
