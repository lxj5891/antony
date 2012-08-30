//package org.sample;
//
//import java.io.File;
//
//
//public class TestVSSDownload_lKF33572 {
//	//Logger logger = LoggerManager.get().getLogger(this.getClass());
//	
//	public void test5(){
//		// 定义用户临时脚本文件的路径
//		String strAntFilePath = null;
//		
//		String strsServerPath = "\\szxnas21-rd\\cp_info_vss";
//		String strUserName = "y104697";
//		String strPassWord = "y104697";
//		
//		String strvVssModulepath = "\\4-增值与服务\\开发库\\03-NQM\\N2510 V200R003C00\\DITA开发-CN\\cfg_n2510";
//		String strVssLocalpath = "c:/lKF33572";
//		// 将用户数据信息写成临时的Ant脚本文件
//		strAntFilePath = this.createVssDownLoadAntFile(strsServerPath, strUserName, strPassWord, strvVssModulepath,
//				strVssLocalpath);
//
//		// 通过ant脚本执行数据的下载
//		if (!this.executeAnt(strAntFilePath))
//		{
//			/*RepositoryReportHelper.addDownLoadMessage("[vss 下载失败]");
//			RepositoryReportHelper.addDownLoadMessage("[请确认 [vssModulePath,vssUsername,vssPassword] 输入正确.因输入不合法, 导致下载数据失败");
//			RepositoryReportHelper.addDownLoadMessage("[请确认输出路径的合法性]");
//			RepositoryReportHelper.addDownLoadMessage("[serverPath] = " + strsServerPath);
//			RepositoryReportHelper.addDownLoadMessage("[vssUsername] = " + strUserName);
//			RepositoryReportHelper.addDownLoadMessage("[vssModulePath] = " + strvVssModulepath);
//			RepositoryReportHelper.addDownLoadMessage("[vssDestDir] = " + strVssLocalpath);*/
//			System.out.println("[vss 下载失败]");
//		}
//		else
//		{
//			String vssCopyToDestDir = "";
//			// 将用户的文档,拷贝到指定的位置
//			//super.copyto(strVssLocalpath, vssCopyToDestDir);
//
//			// 记录运行报告
//			/*RepositoryReportHelper.addDownLoadMessage("[vss 下载成功]");
//			RepositoryReportHelper.addDownLoadMessage("[vsspath] = " + strvVssModulepath);
//			RepositoryReportHelper.addDownLoadMessage("[serverPath] = " + strsServerPath);
//			RepositoryReportHelper.addDownLoadMessage("[vssDestDir] = " + strVssLocalpath);*/
//			System.out.println("[vss 下载成功]");
//		}
//	}
//	
//	/**
//	 * 创建VSS配置库文档下载的可执行Ant脚本
//	 * 
//	 * @param strVssServer
//	 *            服务器地址
//	 * @param strUserName
//	 *            用户名
//	 * @param strPassWord
//	 *            密码
//	 * @param strVssModulePath
//	 *            模块
//	 * @param strDestDirPath
//	 *            下载到本地的位置
//	 * 
//	 * @return String 脚本文件
//	 */
//	private String createVssDownLoadAntFile(String strVssServer, String strUserName,
//			String strPassWord, String strVssModulePath, String strDestDirPath)
//	{
//		// 拷贝SVN模板文件到工程文件夹下
//		String strVssBuildTempSrcFilePath = SystemConfigHelper.get()
//				.getStrVssDownLoadBuildFilePath();
//
//		// 拷贝后的SVN可执行Ant脚本: svndownload_100601195001636(时间戳).xml
//		String strVssBuildDestFolderPath = FileUtils.getFilePath(UserConfigFactory.get()
//				.getRepositoryConfigPath());
//		String strVssBuildDestFilePath = StringUtils.join(strVssBuildDestFolderPath,
//				FileUtils.PATH_SEPARATOR, FileUtils
//						.getFileNameWithoutExt(strVssBuildTempSrcFilePath), "_", DateUtils
//						.getTimeStamp(), ".xml");
//
//		// 拷贝模板文件到指定的目录下
//		FileUtils.copyFile(strVssBuildTempSrcFilePath, strVssBuildDestFilePath);
//
//		// 解析临时的Ant脚本, 注入临时数据
//		Document dom = XmlUtils.getXMLDocFromFile(strVssBuildDestFilePath);
//		String strVssDownLoadMacroFilePath = SystemConfigHelper.get()
//				.getStrVssDownLoadMacroFilePath();
//
//		// 设置执行脚本的basedir为宏定义的路径,否则引用不到lib
//		XmlUtils.setAttribute(dom.getRootElement(), "basedir", FileUtils
//				.getFilePath(strVssDownLoadMacroFilePath));
//
//		// 设置执行脚本到宏代码的引用
//		Element importMacroAntFileEl = (Element) dom.selectSingleNode("//import");
//		XmlUtils.setAttribute(importMacroAntFileEl, "file", strVssDownLoadMacroFilePath);
//
//		// 将用户的数据写入到此临时Ant脚本文件中
//		XmlUtils.setAttribute((Element) dom.selectSingleNode("//property[@name='vssUsername']"),
//				"value", strUserName);
//		XmlUtils.setAttribute((Element) dom.selectSingleNode("//property[@name='vssPassword']"),
//				"value", strPassWord);
//		XmlUtils.setAttribute((Element) dom.selectSingleNode("//property[@name='vssServer']"),
//				"value", strVssServer);
//		XmlUtils.setAttribute((Element) dom.selectSingleNode("//property[@name='vssModulePath']"),
//				"value", strVssModulePath);
//		XmlUtils.setAttribute((Element) dom.selectSingleNode("//property[@name='vssDestDir']"),
//				"value", strDestDirPath);
//
//		// 将文档进行保存
//		XmlUtils.saveXML(dom, strVssBuildDestFilePath);
//
//		// 将生成的带有时间戳的临时ant脚本返回
//		return strVssBuildDestFilePath;
//	}
//	
//	/**
//	 * 编程方式启动ANT执行
//	 * 
//	 * @param buildXml
//	 *            ant的配置文件绝对路径
//	 * 
//	 */
//	public boolean executeAnt(String buildXml)
//	{
//		File buildFile = new File(buildXml);
//		Project p = new Project();
//
//		// 添加事件侦听
//		InvokerListener consoleLogger = new InvokerListener();
//		consoleLogger.setErrorPrintStream(System.err);
//		consoleLogger.setOutputPrintStream(System.out);
//		consoleLogger.setMessageOutputLevel(Project.MSG_INFO);
//		p.addBuildListener(consoleLogger);
//
//		try
//		{
//			p.init();
//			ProjectHelper.configureProject(p, buildFile);
//
//			p.fireBuildStarted();
//			p.executeTarget(p.getDefaultTarget());
//			p.fireBuildFinished(null);
//			return true;
//		}
//		catch (Exception ex)
//		{
//			p.fireBuildFinished(ex);
//			this.logger.error("[AbsRepository error, ant file path is]" + buildXml + ex);
//			return false;
//		}
//	}
//	
//	public static void main(String[] args) {
//		new TestVSSDownload_lKF33572().test5();
//	}
//}
