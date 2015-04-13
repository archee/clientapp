<%@include file="/apps/cq-bootcamp/components/global.jsp"%>

<body>
    <div class="container">


        <div class="page-header">
            <h1>${currentPage.title}</h1>
        </div>


    	<cq:include script="content.jsp"/>
    	<cq:include script="footer.jsp"/>
    </div>

    <cq:include script="javascript.jsp"/>
</body>