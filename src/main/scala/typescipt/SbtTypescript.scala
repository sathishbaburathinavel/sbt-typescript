package typescript

import sbt._
import java.io._
import Keys._

object SbtTypescript extends AutoPlugin {
  override lazy val projectSettings = Seq(commands += compileTS)
   override lazy val buildSettings = Seq(commands += compileTS)
  lazy val compileTS = 
    Command.command("compilets") {(state:State) => 
      import state._
  val projectDir = configuration.baseDirectory
  val batchFileName=projectDir+"\\target\\tscompiler.bat";
  val batchFile=new File(batchFileName)
  if(!batchFile.exists())
  {
  val pw = new PrintWriter(batchFile)
pw.println("@echo off")
pw.println("dir *.ts /b /s >> tsfiles")
pw.println("tsc @tsfiles")
pw.println("del tsfiles")
pw.close
  }
	Process(batchFileName). !< 
      state
    }
}