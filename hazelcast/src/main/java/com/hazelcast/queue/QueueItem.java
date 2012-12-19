/*
 * Copyright (c) 2008-2012, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.queue;

import com.hazelcast.nio.Data;
import com.hazelcast.nio.DataSerializable;
import com.hazelcast.nio.IOUtil;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @ali 12/12/12
 */
public class QueueItem implements DataSerializable {

    private long itemId;

    private Data data;

    QueueItem(){
    }

    QueueItem(long itemId){
        this.itemId = itemId;
    }

    QueueItem(long itemId, Data data){
        this.itemId = itemId;
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public boolean equals(Object obj) {
        if (obj instanceof QueueItem){
            QueueItem other = (QueueItem)obj;
            return data.equals(other.data);
        }
        else if (obj instanceof Data){
            return data.equals(obj);
        }
        return false;
    }

    public void writeData(DataOutput out) throws IOException {
        out.writeLong(itemId);
        IOUtil.writeNullableData(out, data);
    }

    public void readData(DataInput in) throws IOException {
        itemId = in.readLong();
        data = IOUtil.readNullableData(in);
    }
}
