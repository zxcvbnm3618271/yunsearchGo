package com.github.gin.yunsearch.service.jpa;

import com.alibaba.fastjson.JSON;
import com.github.gin.yunsearch.model.*;
import com.github.gin.yunsearch.repository.YunDataRepository;
import com.github.gin.yunsearch.util.JsonUtils;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author GinPonson
 * @since 2017/4/2
 */
@Service
@Transactional
public class YunDataService {

    @Autowired
    private YunDataRepository dataRepository;
    @Autowired
    private EntityManager entityManager;

    @Modifying
    @Transactional
    public void saveIgnore(YunData yunData) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Query query=entityManager.createNativeQuery("insert into yun_data(sharename,size,sharetime,avatarurl,username,pid,uuid,accesscode,state) values (:sharename,:size,:sharetime,:avatarurl,:username,:pid,:uuid,:accesscode,:state)" +
                    "ON DUPLICATE KEY UPDATE " +
                    "sharename=:sharename," +
                    "size=:size," +
                    "sharetime=:sharetime," +
                    "avatarurl=:avatarurl," +
                    "username=:username," +
                    "pid=:pid," +
                    "accesscode=:accesscode," +
                    "state=:state"
            );
            query.setParameter("sharename",yunData.getShareName())
                    .setParameter("uuid",yunData.getUuid())
                    .setParameter("size",yunData.getSize())
                    .setParameter("sharetime",yunData.getShareTime()==null?df.parse(df.format(new Date())):df.format(yunData.getShareTime()))
                    .setParameter("avatarurl",yunData.getAvatarUrl())
                    .setParameter("username",yunData.getUserName())
                    .setParameter("pid",yunData.getPid())
                    .setParameter("accesscode",yunData.getAccessCode())
                    .setParameter("state",yunData.getState())
                    .executeUpdate();
        }catch (Exception e){

        }
//        Query query=entityManager.createNativeQuery("INSERT INTO yun_data(uuid,shareid,dataid,sharename,uk,description,updatetime,sharetime,avatarurl,version,mode,name,size,userid,username,Resource,subnodes,pid,updatedat,createdat,referrers,invalidcount,state,accesscode,type)" +
//                " VALUES (:uuid,:shareid,:dataid,:sharename,:uk,:description,:updatetime,:sharetime,:avatarurl,:version,:mode,:name,:size,:userid,:username,:Resource,:subnodes,:pid,:updatedat,:createdat,:referrers,:invalidcount,:state,:accesscode,:type)" +
//                " ON DUPLICATE KEY UPDATE " +
//                "shareid=:shareid," +
//                "dataid=:dataid," +
//                "sharename=:sharename," +
//                "uk=:uk," +
//                "description=:description," +
//                "updatetime=:updatetime," +
//                "sharetime=:sharetime," +
//                "avatarurl=:avatarurl," +
//                "version=:version," +
//                "mode=:mode," +
//                "name=:name," +
//                "size=:size," +
//                "userid=:userid," +
//                "username=:username," +
//                "Resource=:Resource," +
//                "subnodes=:subnodes," +
//                "pid=:pid," +
//                "updatedat=:updatedat," +
//                "createdat=:createdat," +
//                "referrers=:referrers," +
//                "invalidcount=:invalidcount," +
//                "state=:state," +
//                "accesscode=:accesscode," +
//                "type=:type;");
//        try {
//            query.setParameter("uuid",yunData.getUuid())
//                    .setParameter("shareid",yunData.getShareId())
//                    .setParameter("dataid",yunData.getDataId())
//                    .setParameter("sharename",yunData.getShareName()==null?"":yunData.getShareName())
//                    .setParameter("uk",yunData.getUk())
//                    .setParameter("description",yunData.getDescription()==null?"":yunData.getDescription())
//                    .setParameter("updatetime",yunData.getUpdateTime()==null?df.parse(df.format(new Date())):yunData.getUpdateTime())
//                    .setParameter("sharetime",yunData.getShareTime()==null?df.parse(df.format(new Date())):yunData.getShareTime())
//                    .setParameter("avatarurl",yunData.getAvatarUrl()==null?"":yunData.getAvatarUrl())
//                    .setParameter("version",yunData.getVersion())
//                    .setParameter("mode",yunData.getMode()==null?"":yunData.getMode())
//                    .setParameter("name",yunData.getName()==null?"":yunData.getName())
//                    .setParameter("size",yunData.getSize()==null?"":yunData.getSize())
//                    .setParameter("userid",yunData.getUserId()==null?"":yunData.getUserId())
//                    .setParameter("username",yunData.getUserName()==null?"":yunData.getUserName())
//                    .setParameter("Resource",yunData.getResource()==null?"":yunData.getResource())
//                    .setParameter("subnodes",yunData.getSubnodes()==null?"":yunData.getSubnodes())
//                    .setParameter("pid",yunData.getPid()==null?"":yunData.getPid())
//                    .setParameter("updatedat",yunData.getUpdateAt()==null?df.parse(df.format(new Date())):yunData.getUpdateAt())
//                    .setParameter("createdat",yunData.getCreateAt()==null?df.parse(df.format(new Date())):yunData.getCreateAt())
//                    .setParameter("referrers",yunData.getReferrers()==null?"":yunData.getReferrers())
//                    .setParameter("invalidcount",yunData.getInvalidCount())
//                    .setParameter("state",yunData.getState()==null?"":yunData.getState())
//                    .setParameter("accesscode",yunData.getAccessCode()==null?"":yunData.getAccessCode())
//                    .setParameter("type",yunData.getType()==null?"":yunData.getType())
//                    .executeUpdate();
//        }catch (Exception e){
//
//        }

    }




    public List<YunData> findByUpdateTime(Date updateTime) {
        List<YunData> yunData= dataRepository.findByUpdateTimeGreaterThan(updateTime);
        return yunData;
    }

    public List<YunData> findAll() {
        return dataRepository.findAll();
    }


    public List<referrer> findReferrersByUuid(String uuid){
        Query query=entityManager.createNativeQuery("select referrers as json,uuid from yun_data where " +
                "uuid = ?",jsonString.class);

        List<jsonString> strings=query.setParameter(1,uuid).getResultList();
        List<referrer> referrers=new ArrayList<>();
        referrers=JsonUtils.jsonToList(strings.get(0).getJson(),referrer.class);
        return referrers;
    }

    public List<subnode> findSubnodesByUuid(String uuid){
        Query query=entityManager.createNativeQuery("select subnodes as json,uuid from yun_data where " +
                "uuid = ?",jsonString.class);

        List<jsonString> strings=query.setParameter(1,uuid).getResultList();
        List<subnode> subnodes=new ArrayList<>();
        subnodes=JsonUtils.jsonToList(strings.get(0).getJson(),subnode.class);
        return subnodes;
    }
    @Modifying
    @Transactional
    public Item updateStateByUuid(Item item){
        Query query=entityManager.createNativeQuery("INSERT INTO yun_data (uuid,type,pid,state,createdat,updatedat,invalidcount,updatetime,avatarurl) VALUES (?,?,?,?,?,?,?,?,?)" +
                " ON DUPLICATE KEY UPDATE type=?,pid=?,state=?,updatedat=?,invalidcount=?,updatetime=?,avatarurl=?;");
        query.setParameter(1,item.getUuid())
                .setParameter(2,item.getType())
                .setParameter(3,item.getPid())
                .setParameter(4,item.getState())
                .setParameter(5,item.getCreated_at())
                .setParameter(6,item.getUpdated_at())
                .setParameter(7,item.getInvalid_count())
                .setParameter(8,item.getUpdated_at())
                .setParameter(9,"https://pan.baidu.com/s/1"+item.getPid())
                .setParameter(10,item.getType())
                .setParameter(11,item.getPid())
                .setParameter(12,item.getState())
                .setParameter(13,item.getUpdated_at())
                .setParameter(14,item.getInvalid_count())
                .setParameter(15,item.getUpdated_at())
                .setParameter(16,"https://pan.baidu.com/s/1"+item.getPid())

                .executeUpdate();
        Item newItem=dataRepository.findItemByUuid(item.getUuid()).get(0);
        return newItem;

    }

//    public Item findItemByUuid(String uuid){
//        Query query=entityManager.createNativeQuery()
//    }
    //根据uuid将Resource写入，并返回更新后的item
@Modifying
    @Transactional
    public Item updateResourceByUuid(Resource resource,Item item){
        Query query=entityManager.createNativeQuery("INSERT INTO yun_data (uuid,type,pid,Resource,createdat,updatedat,invalidcount,mode,name,size,sharetime,userid,username,avatarurl) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)" +
                " ON DUPLICATE KEY UPDATE type=?,pid=?,Resource=?,updatedat=?,invalidcount=?,mode=?,name=?,size=?,sharetime=?,userid=?,username=?,avatarurl=?;");
        query.setParameter(1,item.getUuid())
                .setParameter(2,item.getType())
                .setParameter(3,item.getPid())
                .setParameter(4,JsonUtils.objectToJson(resource))
                .setParameter(5,item.getCreated_at())
                .setParameter(6,item.getUpdated_at())
                .setParameter(7,item.getInvalid_count())
                .setParameter(8,resource.getMode())
                .setParameter(9,resource.getName())
                .setParameter(10,resource.getSize())
                .setParameter(11,resource.getShare_time())
                .setParameter(12,resource.getUser_id())
                .setParameter(13,resource.getUser_name())
                .setParameter(14,"https://pan.baidu.com/s/1"+item.getPid())
                .setParameter(15,item.getType())
                .setParameter(16,item.getPid())
                .setParameter(17,JsonUtils.objectToJson(resource))
                .setParameter(18,item.getUpdated_at())
                .setParameter(19,item.getInvalid_count())
                .setParameter(20,resource.getMode())
                .setParameter(21,resource.getName())
                .setParameter(22,resource.getSize())
                .setParameter(23,resource.getShare_time())
                .setParameter(24,resource.getUser_id())
                .setParameter(25,resource.getUser_name())
                .setParameter(26,"https://pan.baidu.com/s/1"+item.getPid())
                .executeUpdate();
        Item newItem=dataRepository.findItemByUuid(item.getUuid()).get(0);
        return newItem;
    }

    //根据uuid将subnodes写入，并返回更新后的item
    @Modifying
    @Transactional
    public Item updateSubnodesByUuid(List<subnode> subnodes,Item item){
        Query query=entityManager.createNativeQuery("INSERT INTO yun_data (uuid,type,pid,subnodes,createdat,updatedat,invalidcount,name,sharename) VALUES (?,?,?,?,?,?,?,?,?)" +
                " ON DUPLICATE KEY UPDATE type=?,pid=?,subnodes=?,updatedat=?,invalidcount=?,name=?,sharename=?;");
        query.setParameter(1,item.getUuid())
                .setParameter(2,item.getType())
                .setParameter(3,item.getPid())
                .setParameter(4,JsonUtils.objectToJson(subnodes))
                .setParameter(5,item.getCreated_at())
                .setParameter(6,item.getUpdated_at())
                .setParameter(7,item.getInvalid_count())
                .setParameter(8,subnodes.get(0).getName())
                .setParameter(9,subnodes.get(0).getName())
                .setParameter(10,item.getType())
                .setParameter(11,item.getPid())
                .setParameter(12,JsonUtils.objectToJson(subnodes))
                .setParameter(13,item.getUpdated_at())
                .setParameter(14,item.getInvalid_count())
                .setParameter(15,subnodes.get(0).getName())
                .setParameter(16,subnodes.get(0).getName())
                .executeUpdate();
        Item newItem=dataRepository.findItemByUuid(item.getUuid()).get(0);
        return newItem;
    }

    //根据user_id返回同一个用户发布的资源
    public List<YunData> findByUserId(String UserId){
        YunData data = new YunData();
        data.setUserId(UserId);

        ExampleMatcher matcher = ExampleMatcher.matching();

        Example<YunData> example = Example.of(data, matcher);
        Sort sort = new Sort(Sort.Direction.DESC, "updateAt");
        return dataRepository.findAll(example, sort);
    }
    public boolean CheckDataByUuid(String uuid,String field){
        Query query=entityManager.createNativeQuery("select ? as json,uuid from yun_data where uuid=?;",jsonString.class);
        List<jsonString> json=query.setParameter(1,field)
                .setParameter(2,uuid).getResultList();
        return json.get(0).getJson()==null?false:true;
    }
    public List<YunData> findByUkAndShareName(long uk, String shareName) {
        YunData data = new YunData();
        data.setUk(uk);
        data.setShareName(shareName);

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("isSingleShare");

        Example<YunData> example = Example.of(data, matcher);
        Sort sort = new Sort(Sort.Direction.DESC, "updateAt");
        return dataRepository.findAll(example, sort);
    }

    public void delete(Iterable<YunData> datas) {
        dataRepository.delete(datas);
    }
    @Modifying
    @Transactional
    public Item updateAccessCodeByUuid(Item item) {
        Query query=entityManager.createNativeQuery("INSERT INTO yun_data (uuid,type,pid,accesscode,createdat,updatedat,invalidcount,avatarurl) VALUES (?,?,?,?,?,?,?,?)" +
                " ON DUPLICATE KEY UPDATE type=?,pid=?,accesscode=?,updatedat=?,invalidcount=?,avatarurl=?;");
        query.setParameter(1,item.getUuid())
                .setParameter(2,item.getType())
                .setParameter(3,item.getPid())
                .setParameter(4,item.getAccess_code())
                .setParameter(5,item.getCreated_at())
                .setParameter(6,item.getUpdated_at())
                .setParameter(7,item.getInvalid_count())
                .setParameter(8,"https://pan.baidu.com/s/1"+item.getPid())
                .setParameter(9,item.getType())
                .setParameter(10,item.getPid())
                .setParameter(11,item.getAccess_code())
                .setParameter(12,item.getUpdated_at())
                .setParameter(13,item.getInvalid_count())
                .setParameter(14,"https://pan.baidu.com/s/1"+item.getPid())
                .executeUpdate();
        Item newItem=dataRepository.findItemByUuid(item.getUuid()).get(0);
        return newItem;
    }
@Modifying
@Transactional
    public Item updateReferrerByUuid(Item item,List<referrer> referrers) {
        Query query=entityManager.createNativeQuery("INSERT INTO yun_data (uuid,type,pid,referrers,createdat,updatedat,invalidcount,avatarurl) VALUES (?,?,?,?,?,?,?,?)" +
                " ON DUPLICATE KEY UPDATE type=?,pid=?,referrers=?,updatedat=?,invalidcount=?,avatarurl=?;");
        query.setParameter(1,item.getUuid())
                .setParameter(2,item.getType())
                .setParameter(3,item.getPid())
                .setParameter(4, JsonUtils.objectToJson(referrers))
                .setParameter(5,item.getCreated_at())
                .setParameter(6,item.getUpdated_at())
                .setParameter(7,item.getInvalid_count())
                .setParameter(8,"https://pan.baidu.com/s/1"+item.getPid())
                .setParameter(9,item.getType())
                .setParameter(10,item.getPid())
                .setParameter(11,JsonUtils.objectToJson(referrers))
                .setParameter(12,item.getUpdated_at())
                .setParameter(13,item.getInvalid_count())
                .setParameter(14,"https://pan.baidu.com/s/1"+item.getPid())
                .executeUpdate();
        Item newItem=dataRepository.findItemByUuid(item.getUuid()).get(0);
        return newItem;
    }
}
