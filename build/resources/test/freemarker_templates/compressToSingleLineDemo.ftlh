<#-- compressToSingleLineDemo.ftlh -->
<#-- custom directive name "compressToSingleLine" is defined as a shared variable. See TestVase.java. -->
<@compressToSingleLine>
  <#assign clazz="nochange">
  <#list segments>
    <span class="code">
    <#items as segment>
      <span class="${clazz}">${segment}</span>
    </#items>
    </span>
  </#list>
</@compressToSingleLine>