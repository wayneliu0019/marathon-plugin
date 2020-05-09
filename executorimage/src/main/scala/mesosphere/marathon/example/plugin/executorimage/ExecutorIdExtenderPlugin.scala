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

    // If custom executor is used and container(or image) is specified
    if (builder.hasExecutor && builder.getExecutor.hasContainer) {

      val container = builder.getExecutorBuilder.getContainerBuilder

      //if taskinfo.container is not set, set it with executorinfo.container
      if (!builder.hasContainer){
         builder.setContainer(container)
      }

      //clear executorinfo's containerinfo
      builder.getExecutorBuilder.clearContainer
      
    }
  }

  override def taskGroup(podSpec: PodSpec, executor: ExecutorInfo.Builder, taskGroup: TaskGroupInfo.Builder): Unit = {}

  override def initialize(marathonInfo: Map[String, Any], configuration: JsObject): Unit = {
    logger.info(s"ExecutorImageExtenderPlugin successfully initialized")
  }
}
