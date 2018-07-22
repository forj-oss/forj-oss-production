
multibranchPipelineJob('jenkins-versions-tool') {
  description('Folder for Project jenkins-versions-tool generated and maintained by Forjj. To update it use forjj update')
  branchSources {
      github {
          repoOwner('forj-oss')
          scanCredentialsId('github-user')
          repository('jenkins-versions-tool')
      }
  }
  orphanedItemStrategy {
      discardOldItems {
          numToKeep(20)
      }
  }
}
