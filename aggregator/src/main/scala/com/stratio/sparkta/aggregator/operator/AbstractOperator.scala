/**
 * Copyright (C) 2014 Stratio (http://stratio.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stratio.sparkta.aggregator.operator

import com.stratio.sparkta.aggregator.domain.Event
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.dstream.DStream

/**
 * Created by ajnavarro on 6/10/14.
 */
abstract class AbstractOperator extends Serializable {

  def process(stream: DStream[Event]): DStream[_]

  def windowStream[U](stream: DStream[U], window: (Option[Long], Option[Long])) = {
    window match {
      case (Some(a), Some(b)) => stream.window(Seconds(a), Seconds(b))
      case (Some(a), None) => stream.window(Seconds(a))
      case _ => stream
    }
  }

}