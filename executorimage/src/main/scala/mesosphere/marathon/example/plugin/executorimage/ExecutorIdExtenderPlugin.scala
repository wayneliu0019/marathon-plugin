package mesosphere.marathon.example.plugin.executorimage

import com.typesafe.scalalogging.StrictLogging
import mesosphere.marathon.plugin.{ApplicationSpec, PodSpec}
import mesosphere.marathon.plugin.plugin.PluginConfiguration
import mesosphere.marathon.plugin.task.RunSpecTaskProcessor
import org.apache.mesos.Protos._
import play.api.libs.json.JsObject

import scala.collection.JavaConverters._

class ExecutorImageExtenderPlugin extends RunSpecTaskProcessor with PluginConfiguration with StrictLogging {

  override def taskInfo(appSpec: ApplicationSpec, builder: TaskInfo.Builder): Unit = {

     logger.info(s"before --- taskinfo marathon build is ${builder.build}")

    // If custom executor is used and container(or image) is specified
    if (builder.hasExecutor && builder.getExecutor.hasContainer) {

      var executor = builder.getExecutorBuilder

      //
      val container = executor.getContainerBuilder
      //if taskinfo.container is not set, set it with executorinfo.container
      if (!builder.hasContainer){
         builder.setContainer(container)
      }

      //set commandInfo {shell:true}
      //if not, the deploy will faile if json's "cmd" is nil
      var command = executor.getCommandBuilder
      command.setShell(true)


      //clear executorinfo's containerinfo
      builder.getExecutorBuilder.clearContainer
      
    }

   logger.info(s"after --- taskinfo marathon build is ${builder.build}")

  }

  override def taskGroup(podSpec: PodSpec, executor: ExecutorInfo.Builder, taskGroup: TaskGroupInfo.Builder): Unit = {}

  override def initialize(marathonInfo: Map[String, Any], configuration: JsObject): Unit = {
    logger.info(s"ExecutorImageExtenderPlugin successfully initialized")
  }
}