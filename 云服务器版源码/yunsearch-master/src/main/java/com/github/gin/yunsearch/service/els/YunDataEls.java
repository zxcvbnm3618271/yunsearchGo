package com.github.gin.yunsearch.service.els;

import com.alibaba.fastjson.JSON;
import com.github.gin.yunsearch.model.YunData;
import com.github.gin.yunsearch.util.JsonUtils;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.elasticsearch.index.search.MultiMatchQuery;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created by GinPonson on 4/7/2017.
 */
@Service
public class YunDataEls {

    private static final Logger LOGGER = LoggerFactory.getLogger(YunDataEls.class);

    private static final String INDEX = "yunsearch";

    @Autowired
    private TransportClient client;

    public YunData getLastData() {
        YunData yunData = null;
        SearchResponse response = client.prepareSearch(INDEX)
                .addSort("updatedat", SortOrder.DESC).setSize(1).get();
        if (response.getHits().getTotalHits() != 0) {
            SearchHit hit = response.getHits().getAt(0);
            yunData = JSON.parseObject(hit.getSourceAsString(), YunData.class);
        }
        return yunData;
    }

    //废除该方法
    public void bulkSave(Iterable<YunData> yunDatas) throws IOException {
        BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();
        for (YunData data : yunDatas) {
            IndexRequestBuilder iresponse =
                    client.prepareIndex(INDEX, "yun_data", data.getUuid())
                            .setSource(jsonBuilder()
                                    .startObject()
                                    .field("share_id", data.getShareId())
                                    .field("data_id", data.getDataId())
                                    .field("share_name", data.getShareName())
                                    .field("uk", data.getUk())
                                    .field("description", data.getDescription())
                                    .field("share_time", data.getShareTime())
                                    .field("avatar_url", data.getAvatarUrl())
                                    .field("update_time", data.getUpdateTime())
                                    .field("user_id",data.getUserId())
                                    .field("user_name",data.getUserName())
                                    .field("Re_source",data.getResource())
                                    .field("subnodes",data.getSubnodes())
                                    .field("uuid",data.getUuid())
                                    .field("p_id",data.getPid())
                                    .field("ty_pe",data.getType())
                                    .field("updated_at",data.getUpdateAt())
                                    .field("created_at",data.getCreateAt())
                                    .field("referrers",data.getReferrers())
                                    .field("invalid_count",data.getInvalidCount())
                                    .field("sta_te",data.getState())
                                    .field("access_code",data.getAccessCode())
                                    .endObject()
                            );
            bulkRequestBuilder.add(iresponse);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("prepared index {}", JSON.toJSONString(data));
            }
        }
        if (bulkRequestBuilder.numberOfActions() != 0)
            bulkRequestBuilder.get();
    }
//删除对应数据行
    public void bulkDelete(Iterable<YunData> yunDatas) {
        BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();
        for (YunData data : yunDatas) {
            DeleteRequestBuilder request = client.prepareDelete(INDEX, "yun_data", data.getUuid());
            bulkRequestBuilder.add(request);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("prepared delete {}", JSON.toJSONString(data));
            }
        }

        if (bulkRequestBuilder.numberOfActions() != 0)
            bulkRequestBuilder.get();
    }

    public List<YunData> findByShareName(String keyword, Integer page, Integer size) {
        SearchRequestBuilder builder = client.prepareSearch(INDEX)
                .setQuery(QueryBuilders.matchQuery("sharename", keyword));
        if (page != null) {
            builder.setFrom((page - 1) * size);
        }
        if (size != null) {
            builder.setSize(size);
        }
        SearchResponse response = builder.get();

        List<YunData> yunDataList = new ArrayList<>();
        for (SearchHit hit : response.getHits().getHits()) {
            YunData data = JSON.parseObject(hit.getSourceAsString(), YunData.class);
            yunDataList.add(data);
        }
        return yunDataList;
    }
    //根据uuid查找提取码
    public String findAccessByUuid(String uuid){
        String type=uuid.substring(0,uuid.indexOf("-"));
        GetResponse response =client.prepareGet(INDEX, type, uuid)
                .setOperationThreaded(false)
                .get();
                    String json=response.getSourceAsString();
YunData yunData= JsonUtils.jsonToPojo(response.getSourceAsString(),YunData.class);

        return yunData==null||yunData.getAccessCode()==null? "null" : yunData.getAccessCode();
    }

    //根据资源名进行匹配查询
    public List<YunData> findByName(String keyword,Integer page,Integer size){
//        SearchRequestBuilder builder = client.prepareSearch(INDEX)
//                .setQuery(QueryBuilders.matchQuery("name", keyword));
//        if (page != null) {
//            builder.setFrom((page - 1) * size);
//        }
//        if (size != null) {
//            builder.setSize(size);
//        }
//        SearchResponse response = builder.get();
//
//        List<YunData> yunDataList = new ArrayList<>();
//        for (SearchHit hit : response.getHits().getHits()) {
//            YunData data = JSON.parseObject(hit.getSourceAsString(), YunData.class);
//            yunDataList.add(data);
//        }
//        return yunDataList;

        WildcardQueryBuilder builder = QueryBuilders.wildcardQuery("name", "*"+keyword+"*");
        SearchResponse response = client.prepareSearch(INDEX).setQuery(builder).setFrom((page - 1) * size).setSize(size).get();
        List<YunData> yunDataList = new ArrayList<>();
        for (SearchHit hit : response.getHits().getHits()) {
            YunData data = JSON.parseObject(hit.getSourceAsString(), YunData.class);
            yunDataList.add(data);
        }
        return yunDataList;
    }

    public Long countByShareName(String keyword) {
        SearchRequestBuilder builder = client.prepareSearch(INDEX)
                .setQuery(QueryBuilders.matchQuery("sharename", keyword));
        SearchResponse response = builder.get();
        return response.getHits().getTotalHits();
    }

    public Long countByName(String keyword){
        SearchRequestBuilder builder = client.prepareSearch(INDEX)
                .setQuery(QueryBuilders.matchQuery("name", keyword));
        SearchResponse response = builder.get();
        return response.getHits().getTotalHits();
    }
}
