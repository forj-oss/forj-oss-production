
multibranchPipelineJob('jenkins-ci') {
  description('Folder for Project jenkins-ci generated and maintained by Forjj. To update it use forjj update')
  branchSources {
      github {
          repoOwner('forj-oss')
          scanCredentialsId('github-user')
          repository('jenkins-ci')
      }
  }
  orphanedItemStrategy {
      discardOldItems {
          numToKeep(20)
      }
  }
}
