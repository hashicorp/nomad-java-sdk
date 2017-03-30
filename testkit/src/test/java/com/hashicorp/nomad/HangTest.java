package com.hashicorp.nomad;/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.hashicorp.nomad.javasdk.NomadApiClient;
import org.apache.http.HttpHost;

import java.io.Console;
import java.net.URI;
import java.net.URISyntaxException;

import static java.sql.DriverManager.println;

public class HangTest {

    public static void main(String[] args) throws URISyntaxException {
        new NomadApiClient(new HttpHost("localhost", 4646));
        System.err.println("Hello");
//        System.out.flush();
    }

}
