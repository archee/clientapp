<%@include file="/apps/cq-bootcamp/components/global.jsp"%>

<div class="row row-buffer">
    <div class="col-md-12">
        <cq:include path="mainpar" resourceType="foundation/components/parsys" />
    </div>
</div>
<div class="row row-buffer">
    <div class="col-md-4">
        <cq:include script="left.jsp" />
    </div>
    <div class="col-md-4">
        <cq:include script="middle.jsp" />
    </div>
    <div class="col-md-4">
        <cq:include script="right.jsp" />
    </div>
</div>