
multibranchPipelineJob('jenkins-install-inits') {
  description('Folder for Project jenkins-install-inits generated and maintained by Forjj. To update it use forjj update')
  branchSources {
      github {
          repoOwner('forj-oss')
          scanCredentialsId('github-user')
          repository('jenkins-install-inits')
      }
  }
  orphanedItemStrategy {
      discardOldItems {
          numToKeep(20)
      }
  }
}
