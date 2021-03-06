/*
 * Copyright 2017 StreamSets Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.streamsets.datacollector.validation;

import com.streamsets.pipeline.api.ErrorCode;

public abstract class IssueCreator {

  public abstract Issue create(ErrorCode error, Object... args);

  public abstract Issue create(String configGroup, ErrorCode error, Object... args);

  public abstract Issue create(String configGroup, String configName, ErrorCode error, Object... args);

  private IssueCreator() {
  }

  public static IssueCreator getPipeline() {
    return new IssueCreator() {
      @Override
      public Issue create(ErrorCode error, Object... args) {
        return new Issue(null, null, null, error, args);
      }

      @Override
      public Issue create(String configGroup, String configName, ErrorCode error, Object... args) {
        return new Issue(null, configGroup, configName, error, args);
      }

      @Override
      public Issue create(String configGroup, ErrorCode error, Object... args) {
        return new Issue(null, configGroup, null, error, args);
      }

    };
  }

  public static IssueCreator getStage(final String instanceName) {
    return new IssueCreator() {
      @Override
      public Issue create(ErrorCode error, Object... args) {
        return new Issue(instanceName, null, null, error, args);
      }

      @Override
      public Issue create(String configGroup, String configName, ErrorCode error, Object... args) {
        return new Issue(instanceName, configGroup, configName, error, args);
      }

      @Override
      public Issue create(String configGroup, ErrorCode error, Object... args) {
        return new Issue(instanceName, configGroup, null, error, args);
      }

    };
  }


}
