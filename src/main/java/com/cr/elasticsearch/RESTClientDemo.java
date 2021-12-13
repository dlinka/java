package com.cr.elasticsearch;

import com.alibaba.fastjson.JSON;
import com.cr.common.Facility;
import com.cr.common.User;
import com.sun.tools.javac.util.Assert;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.Map;

public class RESTClientDemo {

    public static String JAVA_INDEX = "java_index";

    public static RestHighLevelClient client = new RestHighLevelClient(
            RestClient.builder(
                    new HttpHost("localhost", 9200, "http")
            ));

    public static void main(String[] args) throws IOException {
        RESTClientDemo demo = new RESTClientDemo();
        //demo.createIndex();
        //demo.existsIndex();
        //demo.deleteIndex();
        //demo.createDocument();
        //demo.existsDocument();
        //demo.getDocument();
        //demo.updateDocument();
        //demo.deleteDocument();
        //demo.bulkDocument();
        demo.search();

        client.close();
    }

    void createIndex() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(JAVA_INDEX);
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        Facility.print(response.isAcknowledged());
    }

    void existsIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest(JAVA_INDEX);
        boolean response = client.indices().exists(request, RequestOptions.DEFAULT);
        Facility.print(response);
    }

    void deleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest(JAVA_INDEX);
        AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);
        Facility.print(response.isAcknowledged());
    }

    void createDocument() throws IOException {
        User user = new User(1L, "CR", 27);
        IndexRequest request = new IndexRequest(JAVA_INDEX);
        request.id(String.valueOf(user.getUid()));
        request.source(JSON.toJSONString(user), XContentType.JSON);
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        Facility.print(response.status());
    }

    void existsDocument() throws IOException {
        GetRequest request = new GetRequest(JAVA_INDEX, "1");
        boolean exists = client.exists(request, RequestOptions.DEFAULT);
        Facility.print(exists);
    }

    void getDocument() throws IOException {
        GetRequest request = new GetRequest(JAVA_INDEX, "1");
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        Facility.print(response.getSourceAsString());
    }

    void updateDocument() throws IOException {
        UpdateRequest request = new UpdateRequest(JAVA_INDEX, "1");
        request.doc("name", "cr27");
        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
        Facility.print(response.status());
        Facility.print(response.toString());
    }

    void deleteDocument() throws IOException {
        DeleteRequest request = new DeleteRequest(JAVA_INDEX, "1");
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        Facility.print(response);
    }

    void bulkDocument() throws IOException {
        BulkRequest request = new BulkRequest();
        request.add(new IndexRequest(JAVA_INDEX).id(String.valueOf(2)).source(JSON.toJSONString(new User(2L, "zj", 18)), XContentType.JSON));
        request.add(new IndexRequest(JAVA_INDEX).id(String.valueOf(3)).source(JSON.toJSONString(new User(3L, "zgy", 12)), XContentType.JSON));
        request.add(new IndexRequest(JAVA_INDEX).id(String.valueOf(4)).source(JSON.toJSONString(new User(4L, "zzm", 1)), XContentType.JSON));
        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
        Facility.print(response.status());
    }

    void search() throws IOException {
        SearchRequest request = new SearchRequest(JAVA_INDEX);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        MatchQueryBuilder query = QueryBuilders.matchQuery("username", "zj");
        searchSourceBuilder.query(query);
        request.source(searchSourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        Facility.print(JSON.toJSONString(response.getHits()));
        response.getHits().forEach(hit -> {
            Map<String, Object> map = hit.getSourceAsMap();
            for (Map.Entry<String, Object> entry : map.entrySet()){
                Facility.print(entry.getKey() + ":" + entry.getValue());
            }
        });
    }

}
